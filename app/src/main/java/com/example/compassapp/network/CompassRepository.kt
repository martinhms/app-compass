package com.example.compassapp.network

import com.example.compassapp.network.data.ExtractResponse
import javax.inject.Inject


class CompassRepository @Inject constructor(private val api : CompassService) {

    suspend fun every10thCharacter(text: String): ExtractResponse? {
        return api.every10thCharacter(text)
    }
}