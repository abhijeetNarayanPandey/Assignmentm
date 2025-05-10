package com.example.assignmentm.domain.repository

import com.example.assignmentm.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
    suspend fun getProductById(id: Int): Product
}