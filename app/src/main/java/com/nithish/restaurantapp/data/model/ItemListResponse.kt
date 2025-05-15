package com.nithish.restaurantapp.data.model

data class ItemListResponse(
    val responseCode: Int,
    val outcomeCode: Int,
    val responseMessage: String,
    val page: Int,
    val count: Int,
    val totalPages: Int,
    val totalItems: Int,
    val cuisines: List<Cuisine>
)