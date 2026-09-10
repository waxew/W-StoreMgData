package com.wstore.engine.ui.attribute

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.repository.ProductAttributeRepository
import com.wstore.engine.data.repository.ProductRepository
import com.wstore.engine.profile.ProfileRuntimeStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

/**
 * ViewModel عمومی Lookup برای Dynamic Attributeهای Product.
 *
 * کلید Attribute از Schema پروفایل فعال بر اساس moduleId پیدا می‌شود؛ بنابراین ViewModel
 * نام صنف یا نام فایل Profile را Hard Code نمی‌کند و فقط Repositoryهای واقعی را می‌خواند.
 */
@HiltViewModel
class ProductAttributeLookupViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    productRepository: ProductRepository,
    attributeRepository: ProductAttributeRepository
) : ViewModel() {

    private val moduleId: String =
        savedStateHandle[ProductAttributeLookupModule.ARG_MODULE_ID].orEmpty().trim()

    private val definition = ProfileRuntimeStore.visibleAttributes()
        .firstOrNull { attribute ->
            attribute.key.substringAfterLast('.') == moduleId
        }

    private val attributeKey: String = definition?.key.orEmpty()
    private val query = MutableStateFlow("")

    val uiState: StateFlow<ProductAttributeLookupUiState> = combine(
        productRepository.observeProducts(),
        attributeRepository.observeValuesForKey(attributeKey),
        query
    ) { products, valuesByProductId, searchQuery ->
        val normalizedQuery = searchQuery.trim()

        val rows = products.mapNotNull { product ->
            val value = valuesByProductId[product.id]?.trim().orEmpty()
            if (value.isBlank()) {
                null
            } else {
                ProductAttributeLookupRow(
                    productId = product.id,
                    productName = product.name,
                    productCode = product.code,
                    category = product.category,
                    stock = product.stock,
                    attributeValue = value
                )
            }
        }.filter { row ->
            normalizedQuery.isBlank() ||
                row.productName.contains(normalizedQuery, ignoreCase = true) ||
                row.productCode.contains(normalizedQuery, ignoreCase = true) ||
                row.attributeValue.contains(normalizedQuery, ignoreCase = true)
        }

        ProductAttributeLookupUiState(
            moduleId = moduleId,
            title = definition?.label ?: moduleId.ifBlank { "مشخصات کالا" },
            attributeKey = attributeKey,
            query = searchQuery,
            rows = rows,
            schemaAvailable = definition != null
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ProductAttributeLookupUiState(
            moduleId = moduleId,
            title = definition?.label ?: moduleId.ifBlank { "مشخصات کالا" },
            attributeKey = attributeKey,
            schemaAvailable = definition != null
        )
    )

    fun search(value: String) {
        query.value = value
    }
}

data class ProductAttributeLookupUiState(
    val moduleId: String = "",
    val title: String = "",
    val attributeKey: String = "",
    val query: String = "",
    val rows: List<ProductAttributeLookupRow> = emptyList(),
    val schemaAvailable: Boolean = true
)

data class ProductAttributeLookupRow(
    val productId: Long,
    val productName: String,
    val productCode: String,
    val category: String,
    val stock: Int,
    val attributeValue: String
)
