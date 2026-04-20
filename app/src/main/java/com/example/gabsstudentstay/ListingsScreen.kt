package com.example.gabsstudentstay

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.navigation.NavController

@Composable
fun ListingsScreen(viewModel: AuthViewModel, navController: NavController) {
    val listings = viewModel.listings.value

    LaunchedEffect(Unit) {
        viewModel.fetchListings()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Available Listings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(listings) {listing ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp),elevation = CardDefaults.cardElevation(6.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(listing.title, style = MaterialTheme.typography.titleLarge)
                        Text("${listing.location}")
                        Text("${listing.price}")
                        Text("Available: ${listing.availableDate}")
                    }
                }
            }
        }
        Button(
                onClick = {
                    navController.navigate("add_listing")
                }, modifier = Modifier.padding(16.dp)
        ) {Text ("Add Listing")}
    }


}