package com.nithish.restaurantapp.data.repository

import com.nithish.restaurantapp.data.model.Cuisine
import com.nithish.restaurantapp.data.model.Item
import com.nithish.restaurantapp.data.model.PaymentItem
import com.nithish.restaurantapp.data.model.PaymentRequest
import com.nithish.restaurantapp.data.network.RetrofitClient

class RestaurantRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun getCuisines(page: Int = 1, count: Int = 10): List<Cuisine> {
        val response = apiService.getItemList(
            request = mapOf("page" to page, "count" to count)
        )
        return response.cuisines
    }

    suspend fun getItemById(itemId: Int): Item {
        val response = apiService.getItemById(
            request = mapOf("item_id" to itemId)
        )
        return Item(
            id = response.itemId.toString(),
            name = response.itemName,
            imageUrl = response.itemImageUrl,
            price = response.itemPrice.toString(),
            rating = response.itemRating.toString()
        )
    }

    suspend fun makePayment(
        totalAmount: String,
        totalItems: Int,
        items: List<PaymentItem>
    ): String {
        val request = PaymentRequest(
            totalAmount = totalAmount,
            totalItems = totalItems,
            data = items
        )
        val response = apiService.makePayment(request = request)
        return response.txnRefNo
    }
}