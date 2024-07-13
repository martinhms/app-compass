package com.example.compassapp.ui

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

    private val _textInput = MutableLiveData("")
    val textInput: LiveData<String> = _textInput

    private val _resultEvery10Text = MutableLiveData("")
    val resultEvery10Text: LiveData<String> = _resultEvery10Text

    private val _resultWCText = MutableLiveData(String())
    val resultWCText: LiveData<String> = _resultWCText

    init {
        loadSavedData()
    }

    fun onTextInputChanged(newText: String) {
        _textInput.value = newText
        saveCurrentData()
    }

    fun onRequestRunned() {

        viewModelScope.launch {
            val inputText = _textInput.value ?: ""
            val resultEvery10 = every10UseCase.invoke(inputText)?.characters.toString()
            val resultWordCounter = wordCounterUseCase.invoke(inputText).characters.toString()
            _resultEvery10Text.value = resultEvery10
            _resultWCText.value = resultWordCounter
            saveCurrentData()
        }
    }

    private fun loadSavedData() {
        val (loadedTextInput, loadedEvery10thResult, loadedWordCountResult) =
            sharedPreferenceUtils.loadCompassData(context)
        _textInput.value = loadedTextInput
        _resultEvery10Text.value = loadedEvery10thResult
        _resultWCText.value = loadedWordCountResult
    }

    private fun saveCurrentData() {
        sharedPreferenceUtils.saveCompassData(
            context,
            _textInput.value ?: "",
            _resultEvery10Text.value ?: "",
            _resultWCText.value ?: ""
        )
    }

}