package com.example.compassapp.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compassapp.usecases.Every10UseCase
import com.example.compassapp.usecases.WordCounterUseCase
import com.example.compassapp.utils.SharedPreferenceUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val every10UseCase: Every10UseCase,
    private val wordCounterUseCase: WordCounterUseCase,
    private val sharedPreferenceUtils: SharedPreferenceUtils,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _resultEvery10Text = MutableLiveData("")
    val resultEvery10Text: LiveData<String> = _resultEvery10Text

    private val _resultWCText = MutableLiveData(String())
    val resultWCText: LiveData<String> = _resultWCText


    fun onRequestRunned(textInput: String) {

        viewModelScope.launch {
            val resultEvery10 = every10UseCase.invoke(textInput)?.characters.toString()
            val resultWordCounter = wordCounterUseCase.invoke(textInput).characters.toString()
            _resultEvery10Text.value = resultEvery10
            _resultWCText.value = resultWordCounter
        }

    }

    fun preloadCachedData() {
        val cacheEvery = sharedPreferenceUtils.getCachedEvery10thCharacter(context = context)
        val cacheWC = sharedPreferenceUtils.getCachedWordCount(context = context)
        if (cacheEvery != null) {
            _resultEvery10Text.value = cacheEvery.toString()
        }
        if (cacheWC != null) {
            _resultWCText.value = cacheWC.toString()
        }
    }

}