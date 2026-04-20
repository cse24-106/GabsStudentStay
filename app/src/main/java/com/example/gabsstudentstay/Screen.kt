package com.example.gabsstudentstay

sealed class Screen(val route: String) {
    object Auth: Screen("auth")
    object Listings: Screen("listings")
    object AddListing: Screen("add_listing")
}