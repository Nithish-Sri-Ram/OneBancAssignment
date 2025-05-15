package com.nithish.restaurantapp.data.model

data class PaymentResponse(
    val responseCode: Int,
    val outcomeCode: Int,
    val responseMessage: String,
    val txnRefNo: String
)