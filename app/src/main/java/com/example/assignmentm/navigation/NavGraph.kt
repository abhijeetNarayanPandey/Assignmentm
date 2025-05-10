package com.example.assignmentm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.assignmentm.presentation.ui.product.ProductListScreen
import com.example.assignmentm.presentation.ui.product.ProductDetailScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "product_list") {
        composable("product_list") {
            ProductListScreen(navController = navController)
        }
        composable("product_detail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            productId?.let {
                ProductDetailScreen(navController = navController, productId = it)
            }
        }
    }
}