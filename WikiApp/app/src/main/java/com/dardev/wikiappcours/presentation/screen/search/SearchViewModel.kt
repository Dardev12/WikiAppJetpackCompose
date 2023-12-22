package com.dardev.wikiappcours.presentation.screen.search

import androidx.lifecycle.ViewModel
import com.dardev.wikiappcours.data.repository.WikiRepository
import com.dardev.wikiappcours.domain.model.Character

class SearchViewModel(
    private val wikiRepository: WikiRepository
): ViewModel() {
    suspend fun researchACharacter(name:String):List<Character> {
        return wikiRepository.SearchCharacters(name)
    }
}