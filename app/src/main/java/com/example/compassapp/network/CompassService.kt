package com.example.compassapp.network

import com.example.compassapp.network.data.ExtractResponse
import com.example.compassapp.network.data.TextRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompassService @Inject constructor(private val apiCompassClient: ApiCompassClient) {

    suspend fun every10thCharacter(text: String): ExtractResponse? {
        return withContext(Dispatchers.IO) {
            val textRequest = TextRequest(text)
            val resEvery10thCharacter = apiCompassClient.every10thCharacter(textRequest)
            println("Response: $resEvery10thCharacter")
            if (resEvery10thCharacter.isSuccessful) {
                resEvery10thCharacter.body()
            } else {
                null
            }
        }
    }

    suspend fun wordCounter(text: String): Map<String, Int> {
        return withContext(Dispatchers.IO) {
            val textRequest = TextRequest(text)
            val resEvery10thCharacter = apiCompassClient.wordCounter(textRequest)
            println("Response: $resEvery10thCharacter")
            if (resEvery10thCharacter.isSuccessful) {
                resEvery10thCharacter.body() ?: emptyMap()
            } else {
                emptyMap()
            }
        }
    }
}