package com.dardev.wikiappcours.data.repository

import android.content.Context
import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.domain.remote.IWikiApi

class WikiRepository(
    private val wikiApi: IWikiApi
) {

    suspend fun GetListCharacters():List<Character> {
        return wikiApi.GetAllCharacters()
    }

    suspend fun GetCharacterById(id:String): Character? {
        return wikiApi.GetCharacterById(id)
    }

    suspend fun SearchCharacters(name:String): List<Character> {
        return wikiApi.GetCharacterByName(name)
    }
}