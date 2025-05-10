package com.example.assignmentm.presentation.ui.state

import com.example.assignmentm.domain.model.Product

sealed class ProductUiState {
    object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}