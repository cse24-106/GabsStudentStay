package com.example.gabsstudentstay

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddListingScreen(viewModel: AuthViewModel) {

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
        }) {
            Text("Post Listing")
        }

        Spacer(modifier =Modifier.height(8.dp))
        Text(text =viewModel.authState.value)
    }
}