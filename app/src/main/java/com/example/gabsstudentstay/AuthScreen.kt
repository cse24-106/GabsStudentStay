package com.example.gabsstudentstay

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthScreen(authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Student Stay", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")}
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it },
            label = {Text("Password")}
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            authViewModel.register(email, password)
        })
        {Text("Register")}

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            authViewModel.login(email, password)
        })
        {Text("Login")}
        Text(text = authViewModel.authState.value)
    }
}