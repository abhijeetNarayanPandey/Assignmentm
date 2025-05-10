package com.example.assignmentm.presentation.ui.product


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.assignmentm.presentation.viewmodel.ProductViewModel
import com.example.assignmentm.domain.model.Product
import com.example.assignmentm.presentation.ui.state.ProductUiState
import com.skydoves.landscapist.coil.CoilImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (val state = uiState.value) {
                is ProductUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is ProductUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(state.products) { product ->
                            ProductItem(
                                product = product,
                                onClick = {
                                    viewModel.loadProductById(product.id)
                                    navController.navigate("product_detail/${product.id}")
                                }
                            )
                        }
                    }
                }
                is ProductUiState.Error -> {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun ProductItem(product: Product, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            CoilImage(
                imageModel = { product.images.firstOrNull() },
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2
                )
            }
        }
    }
}