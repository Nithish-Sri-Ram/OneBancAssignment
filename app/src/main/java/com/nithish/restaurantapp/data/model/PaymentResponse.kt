package com.nithish.restaurantapp.data.model

import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("outcome_code") val outcomeCode: Int,
    @SerializedName("response_message") val responseMessage: String,
    @SerializedName("txn_ref_no") val txnRefNo: String
)