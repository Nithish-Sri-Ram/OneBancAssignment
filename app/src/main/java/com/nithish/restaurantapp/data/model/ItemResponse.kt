package com.nithish.restaurantapp.data.model

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("outcome_code") val outcomeCode: Int,
    @SerializedName("response_message") val responseMessage: String,
    @SerializedName("cuisine_id") val cuisineId: String,
    @SerializedName("cuisine_name") val cuisineName: String,
    @SerializedName("cuisine_image_url") val cuisineImageUrl: String,
    @SerializedName("item_id") val itemId: Int,
    @SerializedName("item_name") val itemName: String,
    @SerializedName("item_price") val itemPrice: Int,
    @SerializedName("item_rating") val itemRating: Double,
    @SerializedName("item_image_url") val itemImageUrl: String
)