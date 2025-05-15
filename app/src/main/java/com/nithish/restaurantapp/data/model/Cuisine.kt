package com.nithish.restaurantapp.data.model

data class Cuisine(
    val cuisineId: String,
    val cuisineName: String,
    val cuisineImageUrl: String,
    val items: List<Item>
)