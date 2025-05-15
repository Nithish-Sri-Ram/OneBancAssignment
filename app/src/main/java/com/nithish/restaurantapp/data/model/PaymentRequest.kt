package com.nithish.restaurantapp.data.model

import com.google.gson.annotations.SerializedName

data class PaymentRequest(
    @SerializedName("total_amount") val totalAmount: String,
    @SerializedName("total_items") val totalItems: Int,
    @SerializedName("data") val data: List<PaymentItem>
)