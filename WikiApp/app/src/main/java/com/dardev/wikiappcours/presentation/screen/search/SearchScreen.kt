package com.dardev.wikiappcours.presentation.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.presentation.common.ListContent
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val searchText = remember { mutableStateOf("") }
    val characters = remember { mutableStateListOf<Character>() }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchText.value,
                onTextChange = {newText ->
                    searchText.value = newText
                },
                onSearchClicked = {
                    coroutineScope.launch {
                        val newCharacter = viewModel.researchACharacter(it)
                        characters.clear()
                        characters.addAll(newCharacter)
                    }
                },
                onCloseClicked = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            //Ads
        }
    ) {
        Column(
            Modifier.padding(it)
        ) {
            ListContent(
                characters,
                navController
            )
        }

    }
}