package com.dardev.wikiappcours.presentation.screen.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.dardev.wikiappcours.util.PaletteGenerator.convertImageUrlToBitmap
import com.dardev.wikiappcours.util.PaletteGenerator.extractColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(
    navController: NavHostController,
    viewModel: DetailViewModel = koinViewModel()
) {
    val selectedCharacter by viewModel.selectedCharacter.collectAsState()
    val colorPalette by viewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
       //content
        DetailContent(
            navController = navController,
            selectedCharacter = selectedCharacter,
            colors = colorPalette
        )
    } else {
        viewModel.generateColorPalette()
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.GenerateColorPalette -> {
                    val bitmap = convertImageUrlToBitmap(
                        imageUrl = "${selectedCharacter?.picture}",
                        context
                    )
                    if (bitmap != null) {
                        viewModel.setColorPalette(
                            colors = extractColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }
}

sealed class UiEvent{
    object GenerateColorPalette : UiEvent()
}