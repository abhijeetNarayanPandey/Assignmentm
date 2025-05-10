package com.example.assignmentm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentm.domain.model.Product
import com.example.assignmentm.domain.use_case.GetProductByIdUseCase
import com.example.assignmentm.domain.use_case.GetProductsUseCase
import com.example.assignmentm.presentation.ui.state.ProductUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val products = getProductsUseCase()
                _uiState.value = ProductUiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error("Failed to load products: ${e.message}")
            }
        }
    }

    fun loadProductById(id: Int) {
        viewModelScope.launch {
            try {
                _selectedProduct.value = getProductByIdUseCase(id)
            } catch (e: Exception) {
                _selectedProduct.value = null
                _uiState.value = ProductUiState.Error("Failed to load product: ${e.message}")
            }
        }
    }
}

