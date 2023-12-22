package com.dardev.wikiappcours.presentation.screen.home


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.domain.model.Character
import com.dardev.wikiappcours.presentation.common.ListContent
import com.dardev.wikiappcours.presentation.components.ads.BannerAds
import com.dardev.wikiappcours.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.ads.AdSize
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.statusBarColor
    val characters = remember { mutableStateListOf<Character>() }

    LaunchedEffect(key1 = true){
        val newCharacters = viewModel.getListCharacter()
        characters.clear()
        characters.addAll(newCharacters)
    }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }

    Scaffold(
        topBar = {
            HomeTopBar(navController = navController)
        },
        bottomBar = {
            // Ads
            BannerAds(
                size = AdSize.BANNER,
                id = stringResource(id = R.string.banner_ad_id)
            )
        }
    ) {
        Column(
            Modifier.padding(it)
        ) {
            ListContent(
                characters = characters,
                navController = navController
            )
        }
    }
}