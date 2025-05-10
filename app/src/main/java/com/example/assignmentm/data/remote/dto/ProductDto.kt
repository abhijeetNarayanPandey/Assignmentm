package com.example.assignmentm.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags: List<String>,
    val brand: String?,
    val sku: String,
    val weight: Int,
    val dimensions: DimensionsDto,
    @SerializedName("warrantyInformation")
    val warrantyInformation: String,
    @SerializedName("shippingInformation")
    val shippingInformation: String,
    @SerializedName("availabilityStatus")
    val availabilityStatus: String,
    val reviews: List<ReviewDto>,
    @SerializedName("returnPolicy")
    val returnPolicy: String,
    @SerializedName("minimumOrderQuantity")
    val minimumOrderQuantity: Int,
    val meta: MetaDto,
    val thumbnail: String,
    val images: List<String>
)

data class DimensionsDto(
    val width: Double,
    val height: Double,
    val depth: Double
)

data class ReviewDto(
    val rating: Int,
    val comment: String,
    val date: String,
    @SerializedName("reviewerName")
    val reviewerName: String,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String
)

data class MetaDto(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    val barcode: String,
    @SerializedName("qrCode")
    val qrCode: String
)