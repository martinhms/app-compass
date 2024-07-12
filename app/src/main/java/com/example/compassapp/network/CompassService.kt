package com.example.compassapp.network

import android.content.Context
import com.example.compassapp.network.data.ExtractResponse
import com.example.compassapp.network.data.TextRequest
import com.example.compassapp.utils.SharedPreferenceUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CompassService @Inject constructor(
    private val apiCompassClient: ApiCompassClient,
    private val context: Context
) {

    suspend fun every10thCharacter(text: String): ExtractResponse? {
        val cachedResult = SharedPreferenceUtils.getCachedEvery10thCharacter(context)
        if (cachedResult != null) {
            return cachedResult
        }
        return withContext(Dispatchers.IO) {
            val textRequest = TextRequest(text)
            val resEvery10thCharacter = apiCompassClient.every10thCharacter(textRequest)
            println("Response: $resEvery10thCharacter")

            if (resEvery10thCharacter.isSuccessful) {
                val result = resEvery10thCharacter.body()
                if (result != null) {
                    SharedPreferenceUtils.cacheEvery10thCharacter(context, text, result)
                }
                return@withContext result
            } else {
                return@withContext null
            }
        }
    }

    suspend fun wordCounter(text: String): Map<String, Int> {
        val cachedResult = SharedPreferenceUtils.getCachedWordCount(context)
        if (cachedResult != null) {
            return cachedResult
        }
        return withContext(Dispatchers.IO) {
            val textRequest = TextRequest(text)
            val resWordCounter = apiCompassClient.wordCounter(textRequest)
            println("Response: $resWordCounter")
            if (resWordCounter.isSuccessful) {
                val result = resWordCounter.body() ?: emptyMap()
                SharedPreferenceUtils.cacheWordCount(context,text,result)
                return@withContext result

            } else {
                return@withContext emptyMap()
            }
        }
    }
}