package com.nithish.restaurantapp.data.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("price") val price: String,
    @SerializedName("rating") val rating: String
)