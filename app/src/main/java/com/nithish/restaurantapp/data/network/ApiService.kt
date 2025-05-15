package com.nithish.restaurantapp.data.network

import com.nithish.restaurantapp.data.model.ItemListResponse
import com.nithish.restaurantapp.data.model.ItemResponse
import com.nithish.restaurantapp.data.model.PaymentRequest
import com.nithish.restaurantapp.data.model.PaymentResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("/emulator/interview/get_item_list")
    suspend fun getItemList(
        @Header("X-Partner-API-Key") apiKey: String = "uonebancservceemultrS3cg8RaL30",
        @Header("X-Forward-Proxy-Action") action: String = "get_item_list",
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: Map<String, Any>
    ): ItemListResponse

    @POST("/emulator/interview/get_item_by_id")
    suspend fun getItemById(
        @Header("X-Partner-API-Key") apiKey: String = "uonebancservceemultrS3cg8RaL30",
        @Header("X-Forward-Proxy-Action") action: String = "get_item_by_id",
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: Map<String, Any>
    ): ItemResponse

    @POST("/emulator/interview/make_payment")
    suspend fun makePayment(
        @Header("X-Partner-API-Key") apiKey: String = "uonebancservceemultrS3cg8RaL30",
        @Header("X-Forward-Proxy-Action") action: String = "make_payment",
        @Header("Content-Type") contentType: String = "application/json",
        @Body request: PaymentRequest
    ): PaymentResponse
}
