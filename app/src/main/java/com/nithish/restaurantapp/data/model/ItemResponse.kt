package com.nithish.restaurantapp.data.model

data class ItemResponse(
    val responseCode: Int,
    val outcomeCode: Int,
    val responseMessage: String,
    val cuisineId: String,
    val cuisineName: String,
    val cuisineImageUrl: String,
    val itemId: Int,
    val itemName: String,
    val itemPrice: Int,
    val itemRating: Double,
    val itemImageUrl: String
)