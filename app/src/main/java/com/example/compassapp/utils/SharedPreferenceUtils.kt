package com.example.compassapp.utils

import android.content.Context
import com.example.compassapp.network.data.ExtractResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Singleton

@Singleton
object SharedPreferenceUtils {
    private const val WORD_COUNTER_CACHE = "word_counter_cache"
    private const val EVERY_10TH_CHARACTER_CACHE = "every_10th_character_cache"
    private const val ANY_KEY = "any_key"

    fun saveTextData(context: Context, textFieldValue: String, textValue: String) {
        val sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putString("textFieldValue", textFieldValue)
            .putString("textValue", textValue)
            .apply()
    }

    fun loadTextData(context: Context): Pair<String, String> {
        val sharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
        val textFieldValue = sharedPreferences.getString("textFieldValue", "")?: ""
        val textValue = sharedPreferences.getString("textValue", "") ?: ""
        return Pair(textFieldValue, textValue)
    }

    fun cacheWordCount(context: Context, text: String, wordCount: Map<String, Int>) {
        val sharedPreferences =
            context.getSharedPreferences(WORD_COUNTER_CACHE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val jsonString = Gson().toJson(wordCount)
        editor.putString(text, jsonString)
        editor.apply()
    }

    fun getCachedWordCount(context: Context): Map<String, Int>? {
        val sharedPreferences =
            context.getSharedPreferences(WORD_COUNTER_CACHE, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString(ANY_KEY, null)
        return if (jsonString != null) {
            Gson().fromJson(jsonString, object : TypeToken<Map<String, Int>>() {}.type)
        } else {
            null
        }
    }

    fun cacheEvery10thCharacter(context: Context, text: String, extractResponse: ExtractResponse) {
        val sharedPreferences =
            context.getSharedPreferences(EVERY_10TH_CHARACTER_CACHE, Context.MODE_PRIVATE)
        val jsonString = Gson().toJson(extractResponse.characters)
        sharedPreferences.edit().putString(text, jsonString).apply()
    }

    fun getCachedEvery10thCharacter(context: Context): ExtractResponse? {
        val sharedPreferences =
            context.getSharedPreferences(EVERY_10TH_CHARACTER_CACHE, Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString(ANY_KEY, null)
        return if (jsonString != null) {
            Gson().fromJson(jsonString, ExtractResponse::class.java)
        } else {
            null
        }
    }

}