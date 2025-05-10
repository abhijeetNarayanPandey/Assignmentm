package com.example.assignmentm.presentation.ui.product

import com.example.assignmentm.presentation.viewmodel.ProductViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.assignmentm.domain.model.Product
import com.example.assignmentm.presentation.ui.state.ProductUiState
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val selectedProduct = viewModel.selectedProduct.collectAsState().value
    val uiState = viewModel.uiState.collectAsState().value

    // Fetch product if not already loaded
    LaunchedEffect(productId) {
        if (selectedProduct == null || selectedProduct.id != productId) {
            viewModel.loadProductById(productId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                selectedProduct != null -> {
                    ProductDetailContent(product = selectedProduct)
                }
                uiState is ProductUiState.Error -> {
                    Text(
                        text = (uiState as ProductUiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                else -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun ProductDetailContent(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Image Gallery
        if (product.images.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(product.images) { imageUrl ->
                    CoilImage(
                        imageModel = { imageUrl },
                       modifier = Modifier
                            .size(200.dp)
                            .background(Color.LightGray),
                        failure = {
                            Text(
                                text = "Failed to load image",
                                color = MaterialTheme.colorScheme.error,
                                modifier = Modifier
                                    .size(200.dp)
                                    .wrapContentSize(Alignment.Center)
                            )
                        }
                    )
                }
            }
        } else {
            Text(
                text = "No images available",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .wrapContentSize(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product Details
        Text(
            text = product.title,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Category: ${product.category}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Price: $${String.format("%.2f", product.price)}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Rating: ${String.format("%.1f", product.rating)} / 5",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth()
        )
    }
}