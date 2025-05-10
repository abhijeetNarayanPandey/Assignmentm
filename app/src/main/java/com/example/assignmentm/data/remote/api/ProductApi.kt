package com.example.assignmentm.data.remote.api

import com.example.assignmentm.data.remote.dto.ProductDto
import com.example.assignmentm.data.remote.dto.ProductResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

// Note: This API interface is included per the structure but not used due to mock data
interface ProductApi {
    @GET("products")
    suspend fun getProducts(): ProductResponseDto

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto
}