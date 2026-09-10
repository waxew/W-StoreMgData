package com.wstore.engine.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.model.Product
import com.wstore.engine.data.model.ProductDeleteResult
import com.wstore.engine.data.repository.ProductAttributeRepository
import com.wstore.engine.data.repository.ProductProfileRepository
import com.wstore.engine.data.repository.ProductRepository
import com.wstore.engine.profile.ProfileAttributeDefinition
import com.wstore.engine.profile.ProfileRuntimeStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * نام فایل: ProductViewModel.kt
 * ماژول: Product
 * وظیفه: مدیریت لیست کالا، CRUD پایه و Attributeهای پویا بر اساس Business Profile فعال.
 *
 * ذخیره Product و Dynamic Attributeها از ProductProfileRepository و به شکل اتمیک انجام می‌شود.
 * حذف کالا همچنان فقط از مسیر محافظت‌شده ProductRepository عبور می‌کند تا تاریخچه تجاری حفظ شود.
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val attributeRepository: ProductAttributeRepository,
    private val productProfileRepository: ProductProfileRepository
) : ViewModel() {

    /** Schema فیلدهای اختصاصی از Profile فعال می‌آید و نام هیچ صنفی اینجا Hard Code نشده است. */
    val attributeDefinitions: List<ProfileAttributeDefinition> =
        ProfileRuntimeStore.visibleAttributes()

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _editingProduct = MutableStateFlow<Product?>(null)
    val editingProduct: StateFlow<Product?> = _editingProduct.asStateFlow()

    private val _editingAttributeValues = MutableStateFlow<Map<String, String>>(emptyMap())
    val editingAttributeValues: StateFlow<Map<String, String>> =
        _editingAttributeValues.asStateFlow()

    /**
     * کالا و Attributeهای انتخاب‌شده برای صفحه جزئیات.
     * داده‌های اختصاصی از ProductAttributeRepository خوانده می‌شوند و در UI مقدار نمونه ساخته نمی‌شود.
     */
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    private val _selectedAttributeValues = MutableStateFlow<Map<String, String>>(emptyMap())
    val selectedAttributeValues: StateFlow<Map<String, String>> =
        _selectedAttributeValues.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    init {
        observeProducts()
    }

    /**
     * برای سازگاری با فراخوانی‌های قبلی حفظ شده است.
     * Flow اصلی نیز تغییرات دیتابیس را به صورت خودکار دنبال می‌کند.
     */
    fun loadProducts() {
        viewModelScope.launch {
            _allProducts.value = repository.products().map { it.toModel() }
            applyFilter()
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.AddProduct -> addProduct(event)
            is ProductEvent.UpdateProduct -> updateProduct(event)
            is ProductEvent.DeleteProduct -> deleteProduct(event.product)
            is ProductEvent.Search -> search(event.query)
            is ProductEvent.StartEdit -> startEdit(event.product)
            ProductEvent.CancelEdit -> cancelEdit()
            ProductEvent.DismissMessage -> _message.value = null
        }
    }

    fun addProduct(event: ProductEvent.AddProduct) {
        val validationError = validateProductInput(
            name = event.name,
            price = event.price,
            stock = event.stock,
            attributes = event.attributes
        )
        if (validationError != null) {
            _message.value = validationError
            return
        }

        viewModelScope.launch {
            runCatching {
                productProfileRepository.add(
                    product = ProductEntity(
                        name = event.name.trim(),
                        code = event.code.trim(),
                        category = event.category.trim(),
                        price = event.price,
                        stock = event.stock
                    ),
                    attributes = event.attributes,
                    definitions = attributeDefinitions
                )
            }.onSuccess {
                _message.value = "کالا و مشخصات اختصاصی آن با موفقیت ثبت شد."
            }.onFailure { error ->
                _message.value = error.message ?: "ثبت کالا انجام نشد."
            }
        }
    }

    private fun updateProduct(event: ProductEvent.UpdateProduct) {
        val validationError = validateProductInput(
            name = event.name,
            price = event.price,
            stock = 0,
            attributes = event.attributes
        )
        if (validationError != null) {
            _message.value = validationError
            return
        }

        viewModelScope.launch {
            runCatching {
                val current = repository.getById(event.id)
                    ?: error("کالای انتخاب‌شده در دیتابیس پیدا نشد.")

                productProfileRepository.update(
                    product = current.copy(
                        name = event.name.trim(),
                        code = event.code.trim(),
                        category = event.category.trim(),
                        price = event.price
                    ),
                    attributes = event.attributes,
                    definitions = attributeDefinitions
                )
            }.onSuccess {
                val selected = _selectedProduct.value
                cancelEdit()
                if (selected?.id == event.id) {
                    selectProduct(selected.copy(
                        name = event.name.trim(),
                        code = event.code.trim(),
                        category = event.category.trim(),
                        price = event.price
                    ))
                }
                _message.value = "اطلاعات کالا و مشخصات اختصاصی آن ویرایش شد."
            }.onFailure { error ->
                _message.value = error.message ?: "ویرایش کالا انجام نشد."
            }
        }
    }

    private fun startEdit(product: Product) {
        clearSelectedProduct()
        _editingProduct.value = product
        _editingAttributeValues.value = emptyMap()

        viewModelScope.launch {
            runCatching {
                attributeRepository.getValues(product.id)
            }.onSuccess { values ->
                // اگر کاربر در این فاصله کالای دیگری را انتخاب کرده باشد مقدار قبلی اعمال نمی‌شود.
                if (_editingProduct.value?.id == product.id) {
                    _editingAttributeValues.value = values
                }
            }.onFailure { error ->
                _message.value = error.message ?: "خواندن مشخصات اختصاصی کالا انجام نشد."
            }
        }
    }

    /**
     * جزئیات محصول را از داده واقعی باز می‌کند و Attributeهای ذخیره‌شده را جداگانه می‌خواند.
     */
    fun selectProduct(product: Product) {
        _selectedProduct.value = product
        _selectedAttributeValues.value = emptyMap()

        viewModelScope.launch {
            runCatching {
                attributeRepository.getValues(product.id)
            }.onSuccess { values ->
                if (_selectedProduct.value?.id == product.id) {
                    _selectedAttributeValues.value = values
                }
            }.onFailure { error ->
                _message.value = error.message ?: "خواندن جزئیات اختصاصی کالا انجام نشد."
            }
        }
    }

    fun clearSelectedProduct() {
        _selectedProduct.value = null
        _selectedAttributeValues.value = emptyMap()
    }

    private fun cancelEdit() {
        _editingProduct.value = null
        _editingAttributeValues.value = emptyMap()
    }

    private fun deleteProduct(product: Product) {
        viewModelScope.launch {
            runCatching {
                repository.delete(product.toEntity())
            }.onSuccess { result ->
                if (result == ProductDeleteResult.Deleted && _selectedProduct.value?.id == product.id) {
                    clearSelectedProduct()
                }
                _message.value = when (result) {
                    ProductDeleteResult.Deleted -> "کالا حذف شد."
                    ProductDeleteResult.NotFound -> "کالا قبلاً حذف شده یا پیدا نشد."
                    is ProductDeleteResult.BlockedByHistory -> {
                        "این کالا سابقه فروش/فاکتور دارد و برای حفظ تاریخچه قابل حذف نیست."
                    }
                }
            }.onFailure { error ->
                _message.value = error.message ?: "حذف کالا انجام نشد."
            }
        }
    }

    private fun search(query: String) {
        _query.value = query
        applyFilter()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            repository.observeProducts().collect { entities ->
                _allProducts.value = entities.map { it.toModel() }
                applyFilter()

                val selectedId = _selectedProduct.value?.id
                if (selectedId != null) {
                    val refreshed = _allProducts.value.firstOrNull { it.id == selectedId }
                    if (refreshed == null) {
                        clearSelectedProduct()
                    } else {
                        _selectedProduct.value = refreshed
                    }
                }
            }
        }
    }

    private fun applyFilter() {
        val normalizedQuery = _query.value.trim()
        val source = _allProducts.value

        _products.value = if (normalizedQuery.isBlank()) {
            source
        } else {
            source.filter { product ->
                product.name.contains(normalizedQuery, ignoreCase = true) ||
                    product.code.contains(normalizedQuery, ignoreCase = true) ||
                    product.category.contains(normalizedQuery, ignoreCase = true)
            }
        }
    }

    private fun validateProductInput(
        name: String,
        price: Double,
        stock: Int,
        attributes: Map<String, String>
    ): String? {
        if (name.isBlank()) return "نام کالا الزامی است."
        if (price < 0.0) return "قیمت کالا نمی‌تواند منفی باشد."
        if (stock < 0) return "موجودی اولیه نمی‌تواند منفی باشد."

        attributeDefinitions.forEach { definition ->
            val value = attributes[definition.key].orEmpty().trim()
            if (definition.required && value.isBlank()) {
                return "فیلد «${definition.label}» الزامی است."
            }
            if (value.isBlank()) return@forEach

            when (definition.type) {
                "number" -> if (value.toLongOrNull() == null) {
                    return "مقدار «${definition.label}» باید عدد صحیح باشد."
                }
                "decimal" -> if (value.toDoubleOrNull() == null) {
                    return "مقدار «${definition.label}» باید عدد معتبر باشد."
                }
                "option" -> if (value !in definition.options) {
                    return "گزینه انتخاب‌شده برای «${definition.label}» معتبر نیست."
                }
                "boolean" -> if (value != "true" && value != "false") {
                    return "مقدار «${definition.label}» معتبر نیست."
                }
            }
        }

        return null
    }

    private fun ProductEntity.toModel(): Product = Product(
        id = id,
        name = name,
        code = code,
        category = category,
        price = price,
        stock = stock
    )

    private fun Product.toEntity(): ProductEntity = ProductEntity(
        id = id,
        name = name,
        code = code,
        category = category,
        price = price,
        stock = stock
    )
}
