package com.example.compassapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compassapp.usecases.Every10UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(private val every10UseCase: Every10UseCase) :
    ViewModel() {

    private val _resultText = MutableLiveData("")
    val resultText: LiveData<String> = _resultText

    fun onRequestRunned(textInput: String) {
        viewModelScope.launch {
            val result = every10UseCase.invoke(textInput)?.characters.toString()

            _resultText.value = result
        }

    }

}