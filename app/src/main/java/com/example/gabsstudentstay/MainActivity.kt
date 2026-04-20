package com.example.gabsstudentstay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val viewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isLoggedIn = viewModel.isLoggedIn.value
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = if (isLoggedIn) Screen.Listings.route else Screen.Auth.route
            ) {
                composable (Screen.Auth.route) {
                    AuthScreen(authViewModel = viewModel)

                    //Navigate after login
                    if(viewModel.isLoggedIn.value) {
                        navController.navigate(Screen.Listings.route) {
                            popUpTo(Screen.Auth.route) {inclusive = true}
                        }
                    }
                }

                composable(Screen.Listings.route) {
                    ListingsScreen(viewModel, navController)
                }

                composable(Screen.AddListing.route) {
                    AddListingScreen(viewModel, navController)
                }
            }
        }
    }
}