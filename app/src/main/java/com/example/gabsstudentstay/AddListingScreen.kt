package com.example.gabsstudentstay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AddListingScreen(viewModel: AuthViewModel, navController: NavController) {

    var title by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("")}
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text("Add Listing", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(title, {title = it}, label ={Text("Title") })
        OutlinedTextField(price, {price = it}, label ={Text("Price")})
        OutlinedTextField(location, {location = it}, label ={Text("Location")})
        OutlinedTextField(description, {description = it}, label ={Text("Description")})
        OutlinedTextField(date, {date = it}, label ={Text("Available Date")})

        Spacer(modifier =Modifier.height(16.dp))
        Button(onClick ={
            viewModel.addListing(title, price, location, description, date)
            navController.popBackStack()
        }) {
            Text("Post Listing")
        }

        Spacer(modifier =Modifier.height(8.dp))
        Text(text =viewModel.authState.value)
    }
}