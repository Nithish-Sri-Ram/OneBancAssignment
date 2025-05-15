package com.nithish.restaurantapp.data.model

data class PaymentRequest(
    val totalAmount: String,
    val totalItems: Int,
    val data: List<PaymentItem>
)