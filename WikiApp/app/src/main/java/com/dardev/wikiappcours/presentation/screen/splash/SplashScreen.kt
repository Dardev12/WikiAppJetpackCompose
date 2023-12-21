package com.dardev.wikiappcours.presentation.screen.splash

import android.content.res.Configuration
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dardev.wikiappcours.R
import com.dardev.wikiappcours.navigation.Screen
import com.dardev.wikiappcours.ui.theme.LightModeP
import com.dardev.wikiappcours.ui.theme.LightModePV
import com.dardev.wikiappcours.ui.theme.NightModePV
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = koinViewModel()
) {
    val onBoardingCompleted by viewModel.onBoardingCompleted.collectAsState()
    val degrees = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted){
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Splash(degrees = degrees.value)
}

@Composable
fun Splash(degrees:Float){
    if(isSystemInDarkTheme()){
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(LightModeP, LightModeP, NightModePV)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.app_name)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(LightModeP, LightModePV, LightModePV)))
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                modifier = Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = stringResource(id = R.string.app_name)
            )
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview(){
    Splash(degrees = 0f)
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
fun SplashScreenDarkPreview(){
    Splash(degrees = 0f)
}