package com.example.assignmentm.domain.use_case

import com.example.assignmentm.domain.model.Product
import com.example.assignmentm.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return repository.getProducts()
    }
}