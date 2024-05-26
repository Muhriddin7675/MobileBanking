package com.example.mobilebanking.data.remote.api

import com.example.mobilebanking.data.remote.request.CardRequest
import com.example.mobilebanking.data.remote.request.UpdateCardRequest
import com.example.mobilebanking.data.remote.request.UpdateTokenRequest
import com.example.mobilebanking.data.remote.request.UpdateTokenResponse
import com.example.mobilebanking.data.remote.response.CardResponse
import com.example.mobilebanking.data.remote.response.GetCardOwnerRequest
import com.example.mobilebanking.data.remote.response.GetCardOwnerResponse
import com.example.mobilebanking.data.remote.response.MessageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AddCardApi {

    @GET("mobile-bank/v1/card")
    suspend fun getCards(): Response<List<CardResponse>>

    @POST("mobile-bank/v1/card")
    suspend fun addCard(@Body request: CardRequest): Response<MessageResponse>

    @PUT("mobile-bank/v1/card")
    suspend fun updateCard(@Body updateCardRequest: UpdateCardRequest): Response<MessageResponse>

    @POST("mobile-bank/v1/auth/update-token")
    fun refreshToken(@Body data: UpdateTokenRequest): Call<UpdateTokenResponse>

    @DELETE("mobile-bank/v1/card/{id}")
    suspend fun deleteCard(@Path("id") id: String): Response<MessageResponse>

    @POST("mobile-bank/v1/transfer/card-owner")
    suspend fun getCardOwner(@Body request: GetCardOwnerRequest) : Response<GetCardOwnerResponse>


}