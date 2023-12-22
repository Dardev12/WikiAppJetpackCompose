package com.dardev.wikiappcours.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dardev.wikiappcours.presentation.screen.home.HomeScreen
import com.dardev.wikiappcours.presentation.screen.splash.SplashScreen
import com.dardev.wikiappcours.presentation.screen.welcome.WelcomeScreen
import com.dardev.wikiappcours.util.Constants.DETAILS_ARGUMENT_KEY

@Composable
fun NavModel(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ){
        composable(route = Screen.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY){
                type = NavType.StringType
            })
        ){

        }
        composable(route = Screen.Search.route){

        }
    }
}