package business_profile.inventory

import business_profile.schema.AttributeValue

/**
 * آداپتور اتصال ویژگی های پویا به ماژول موجودی
 * بدون تغییر در Core Inventory
 */
class InventoryAttributeAdapter {
    fun attachAttributes(
        inventoryId: String,
        attributes: List<AttributeValue>
    ): InventoryDynamicAttributes {
        return InventoryDynamicAttributes(
            inventoryId = inventoryId,
            attributes = attributes
        )
    }
}

data class InventoryDynamicAttributes(
    val inventoryId: String,
    val attributes: List<AttributeValue>
)
