package com.wstore.engine.domain.product.usecase

import com.wstore.engine.core.product.repository.ProductAttributeRepository

class SaveProductAttributesUseCase(
    private val repository: ProductAttributeRepository
) {
    suspend operator fun invoke(productId: Long, attributes: List<Pair<String, String>>) {
        repository.saveAttributes(productId, attributes)
    }
}
