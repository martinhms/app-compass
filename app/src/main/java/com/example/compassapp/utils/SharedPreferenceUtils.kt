package com.example.compassapp.utils

import android.content.Context
import com.example.compassapp.network.data.ExtractResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Singleton

@Singleton
object SharedPreferenceUtils {
    private const val APP_DATA = "app_data"
    private const val TEXT_INPUT = "textInput"
    private const val WORD_COUNTER_CACHE = "word_counter_cache"
    private const val EVERY_10TH_CHARACTER_CACHE = "every_10th_character_cache"

    fun saveCompassData(
        context: Context,
        textInput: String,
        every10thResult: String,
        wordCountResult: String
    ) {
        val sharedPreferences = context.getSharedPreferences(APP_DATA, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(TEXT_INPUT, textInput)
            putString(EVERY_10TH_CHARACTER_CACHE, every10thResult)
            putString(WORD_COUNTER_CACHE, wordCountResult)
            apply()
        }
    }

    fun loadCompassData(context: Context): Triple<String, String, String> {
        val sharedPreferences = context.getSharedPreferences(APP_DATA, Context.MODE_PRIVATE)
        val textInput = sharedPreferences.getString(TEXT_INPUT, "") ?: ""
        val every10thResult = sharedPreferences.getString(EVERY_10TH_CHARACTER_CACHE, "") ?: ""
        val wordCountResult = sharedPreferences.getString(WORD_COUNTER_CACHE, "") ?: ""
        return Triple(textInput, every10thResult, wordCountResult)
    }

}