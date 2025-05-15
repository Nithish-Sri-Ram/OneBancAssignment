package com.nithish.restaurantapp.data.model

data class PaymentItem(
    val cuisineId: Int,
    val itemId: Int,
    val itemPrice: Int,
    val itemQuantity: Int
)