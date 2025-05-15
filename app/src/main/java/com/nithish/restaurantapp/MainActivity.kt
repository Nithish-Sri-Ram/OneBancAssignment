package com.nithish.restaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nithish.restaurantapp.ui.screens.CartScreen
import com.nithish.restaurantapp.ui.screens.CuisineScreen
import com.nithish.restaurantapp.ui.screens.HomeScreen
import com.nithish.restaurantapp.ui.theme.RestaurantAppTheme
import com.nithish.restaurantapp.ui.viewmodel.RestaurantViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RestaurantAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RestaurantApp()
                }
            }
        }
    }
}

@Composable
fun RestaurantApp() {
    val navController = rememberNavController()
    val viewModel: RestaurantViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onCuisineClick = { cuisine ->
                    viewModel.selectCuisine(cuisine)
                    navController.navigate("cuisine")
                },
                onCartClick = { navController.navigate("cart") }
            )
        }

        composable("cuisine") {
            CuisineScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate("cart") }
            )
        }

        composable("cart") {
            CartScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onOrderSuccess = { navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }}
            )
        }
    }
}