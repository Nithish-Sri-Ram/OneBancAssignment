package com.nithish.restaurantapp.data.model

data class CartItem(
    val cuisineId: String,
    val cuisineName: String,
    val itemId: String,
    val itemName: String,
    val itemPrice: Double,
    val itemImageUrl: String,
    val quantity: Int = 1
)