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

            if (resEvery10thCharacter.isSuccessful) {
                val result = resEvery10thCharacter.body()
                return@withContext result
            } else {
                return@withContext null
            }
        }
    }

    suspend fun wordCounter(text: String): Map<String, Int> {
        return withContext(Dispatchers.IO) {
            val textRequest = TextRequest(text)
            val resWordCounter = apiCompassClient.wordCounter(textRequest)

            if (resWordCounter.isSuccessful) {
                val result = resWordCounter.body() ?: emptyMap()
                return@withContext result

            } else {
                return@withContext emptyMap()
            }
        }
    }
}