package com.example.assignmentm.domain.use_case

import com.example.assignmentm.domain.model.Product
import com.example.assignmentm.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(id: Int): Product {
        return repository.getProductById(id)
    }
}