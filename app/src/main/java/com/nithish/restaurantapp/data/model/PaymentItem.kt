package com.nithish.restaurantapp.data.model

import com.google.gson.annotations.SerializedName

data class PaymentItem(
    @SerializedName("cuisine_id") val cuisineId: Int,
    @SerializedName("item_id") val itemId: Int,
    @SerializedName("item_price") val itemPrice: Int,
    @SerializedName("item_quantity") val itemQuantity: Int
)