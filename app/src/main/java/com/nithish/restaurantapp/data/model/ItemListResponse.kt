package com.nithish.restaurantapp.data.model

import com.google.gson.annotations.SerializedName

data class ItemListResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("outcome_code") val outcomeCode: Int,
    @SerializedName("response_message") val responseMessage: String,
    @SerializedName("page") val page: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_items") val totalItems: Int,
    @SerializedName("cuisines") val cuisines: List<Cuisine>
)