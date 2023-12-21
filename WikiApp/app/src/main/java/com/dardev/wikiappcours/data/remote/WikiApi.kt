package com.dardev.wikiappcours.data.remote

import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.domain.remote.IWikiApi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

class WikiApi:IWikiApi {
    private val db = FirebaseFirestore.getInstance()
    override suspend fun GetAllCharacters(): List<Character> {
        return try {
            db.collection("character")
                .get()
                .await()
                .documents
                .mapNotNull { document ->
                    document.toObject<Character>()?.copy(id = document.id)
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun GetCharacterById(id: String): Character? {
        return try {
            val document = db.collection("character").document(id).get().await()
            if (document.exists()) {
                document.toObject<Character>()?.copy(id = document.id)
            } else {
                null
            }
        } catch (e: Exception){
            null
        }
    }

    override suspend fun GetCharacterByName(name: String): List<Character> {
        return try {
            val allCharacter = db.collection("character")
                .get()
                .await()
                .documents
                .mapNotNull { document ->
                    document.toObject<Character>()?.copy(id = document.id)
                }
            allCharacter.filter { character ->
                character.name.contains(name, ignoreCase = true)
            }
        } catch (e:Exception) {
            emptyList()
        }
    }
}