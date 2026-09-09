package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.model.Product

/**
 * داده فرم کالا در لایه UI.
 * Entity دیتابیس عمداً وارد Composable نمی‌شود.
 */
data class ProductFormData(
    val name: String,
    val code: String,
    val category: String,
    val price: Double,
    val stock: Int
)

/**
 * فرم مشترک ثبت و ویرایش کالا.
 * در حالت ویرایش، موجودی فقط خواندنی است و تغییر موجودی باید از ماژول Inventory انجام شود.
 */
@Composable
fun AddProductForm(
    initialProduct: Product? = null,
    onSave: (ProductFormData) -> Unit,
    onCancel: () -> Unit = {}
) {
    var name by remember(initialProduct?.id) { mutableStateOf(initialProduct?.name.orEmpty()) }
    var code by remember(initialProduct?.id) { mutableStateOf(initialProduct?.code.orEmpty()) }
    var category by remember(initialProduct?.id) { mutableStateOf(initialProduct?.category.orEmpty()) }
    var price by remember(initialProduct?.id) {
        mutableStateOf(initialProduct?.price?.toString().orEmpty())
    }
    var stock by remember(initialProduct?.id) {
        mutableStateOf(initialProduct?.stock?.toString().orEmpty())
    }
    var formError by remember(initialProduct?.id) { mutableStateOf<String?>(null) }

    val isEditing = initialProduct != null

    Column {
        Text(if (isEditing) "ویرایش کالا" else "ثبت کالای جدید")

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("نام کالا") }
        )
        OutlinedTextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("کد کالا") }
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("دسته‌بندی") }
        )
        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("قیمت") }
        )
        OutlinedTextField(
            value = stock,
            onValueChange = { if (!isEditing) stock = it },
            enabled = !isEditing,
            label = { Text(if (isEditing) "موجودی (از Inventory تغییر می‌کند)" else "موجودی اولیه") }
        )

        formError?.let { Text(it) }

        Row {
            Button(
                onClick = {
                    val parsedPrice = price.toDoubleOrNull()
                    val parsedStock = stock.toIntOrNull()

                    formError = when {
                        name.isBlank() -> "نام کالا الزامی است."
                        parsedPrice == null -> "قیمت کالا معتبر نیست."
                        parsedPrice < 0.0 -> "قیمت کالا نمی‌تواند منفی باشد."
                        parsedStock == null -> "موجودی معتبر نیست."
                        parsedStock < 0 -> "موجودی نمی‌تواند منفی باشد."
                        else -> null
                    }

                    if (formError == null) {
                        onSave(
                            ProductFormData(
                                name = name.trim(),
                                code = code.trim(),
                                category = category.trim(),
                                price = parsedPrice!!,
                                stock = if (isEditing) initialProduct!!.stock else parsedStock!!
                            )
                        )

                        if (!isEditing) {
                            name = ""
                            code = ""
                            category = ""
                            price = ""
                            stock = ""
                        }
                    }
                }
            ) {
                Text(if (isEditing) "ذخیره تغییرات" else "ثبت کالا")
            }

            if (isEditing) {
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(onClick = onCancel) {
                    Text("انصراف")
                }
            }
        }
    }
}
