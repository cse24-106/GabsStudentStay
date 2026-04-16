package com.example.gabsstudentstay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    private val viewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isLoggedIn = viewModel.isLoggedIn.value

            if (isLoggedIn){
                AddListingScreen(viewModel)
            }else {
                AuthScreen(authViewModel = viewModel)
            }
        }
    }
}