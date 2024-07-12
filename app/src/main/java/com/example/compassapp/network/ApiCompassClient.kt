package com.example.compassapp.network

import com.example.compassapp.network.data.ExtractResponse
import com.example.compassapp.network.data.TextRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiCompassClient {

    @POST("api/every10thCharacter")
    suspend fun every10thCharacter(@Body request: TextRequest): Response<ExtractResponse>

}