package com.example.compassapp.usecases

import com.example.compassapp.domain.Every10thCharacterDomain
import com.example.compassapp.domain.WordCounterDomain
import com.example.compassapp.network.CompassRepository
import com.example.compassapp.network.data.WordCounterResponse
import javax.inject.Inject

class WordCounterUseCase @Inject constructor(private val repository: CompassRepository) {

    suspend operator fun invoke(text: String): WordCounterDomain{
        val response = repository.wordCounter(text)
        return WordCounterDomain(response)
    }
}