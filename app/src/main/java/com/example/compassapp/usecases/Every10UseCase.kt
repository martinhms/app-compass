package com.example.compassapp.usecases

import com.example.compassapp.domain.Every10thCharacterDomain
import com.example.compassapp.network.CompassRepository
import javax.inject.Inject

class Every10UseCase @Inject constructor(private val repository: CompassRepository) {

    suspend operator fun invoke(text: String): Every10thCharacterDomain? {
        val response = repository.every10thCharacter(text)
        return if (response != null) {
            Every10thCharacterDomain(response.characters)
        } else {
            null
        }
    }
}