package com.dardev.wikiappcours.domain.remote

import com.dardev.wikiappcours.domain.model.Character

interface IWikiApi {

    suspend fun  GetAllCharacters(): List<Character>

    suspend fun  GetCharacterById(id:String):Character?

    suspend fun GetCharacterByName(name: String): List<Character>
}