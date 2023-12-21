package com.dardev.wikiappcours.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.domain.remote.IWikiApi
import com.dardev.wikiappcours.util.Constants.PREFERENCES_KEY
import com.dardev.wikiappcours.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)
class WikiRepository(
    private val wikiApi: IWikiApi,
    context: Context
) {
    private object PreferencesKey{
        val onBoardingKey = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    private val dataStore = context.dataStore

    suspend fun GetListCharacters():List<Character> {
        return wikiApi.GetAllCharacters()
    }

    suspend fun GetCharacterById(id:String): Character? {
        return wikiApi.GetCharacterById(id)
    }

    suspend fun SearchCharacters(name:String): List<Character> {
        return wikiApi.GetCharacterByName(name)
    }

    suspend fun saveOnBoardingState(completed:Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }
    fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException){
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}