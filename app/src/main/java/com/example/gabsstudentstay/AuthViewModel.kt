package com.example.gabsstudentstay

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val db = FirebaseFirestore.getInstance()

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    private val _authState = mutableStateOf("")
    val authState: State<String> = _authState

    private val _listings = mutableStateOf<List<Listing>>(emptyList())
    val listings: State<List<Listing>> = _listings

    fun register(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userId = auth.currentUser?.uid ?: ""

                val user = User(
                    userId = userId,
                    email = email,
                    role = "student"
                )

                db.collection("users").document(userId).set(user).addOnSuccessListener {
                    _authState.value = "Registered and Saved"
                    _isLoggedIn.value = true
                }
                    .addOnFailureListener { e ->
                        _authState.value = "Firestore error: ${e.message}"
                    }
            } else {
                _authState.value = "${it.exception?.message}"
            }
        }
    }

    fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _authState.value = "Logged In successfully"
                _isLoggedIn.value = true
            } else {
                _authState.value = "${it.exception?.message}"
            }
        }
    }

    fun addListing(
        title: String,
        price: String,
        location: String,
        description: String,
        availableDate: String
    ) {
        val userId = auth.currentUser?.uid ?: return

        val listingId = db.collection("listings").document().id

        val listing = Listing(
            listingId = listingId,
            title = title,
            price = price,
            location = location,
            description = description,
            availableDate = availableDate,
            ownerId = userId
        )

        db.collection("listings").document(listingId).set(listing).addOnSuccessListener {
            _authState.value = "Listing added"
        } .addOnFailureListener {
            _authState.value = "${it.message}"
        }
    }

    fun fetchListings() {
        db.collection("listings").get().addOnSuccessListener { result ->
            val list = result.documents.mapNotNull {
                it.toObject(Listing::class.java)
            }
            _listings.value = list
        }
    }
}