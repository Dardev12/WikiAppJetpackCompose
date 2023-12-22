package com.dardev.wikiappcours.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.dardev.wikiappcours.data.repository.WikiRepository
import com.dardev.wikiappcours.domain.model.Character

class HomeViewModel(
    private val wikiRepository: WikiRepository
): ViewModel() {

    suspend fun getListCharacter():List<Character> {
        return wikiRepository.GetListCharacters()
    }
}