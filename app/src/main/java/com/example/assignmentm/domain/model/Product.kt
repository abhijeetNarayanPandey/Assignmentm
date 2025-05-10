package com.example.assignmentm.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val category: String,
    val images: List<String>
)