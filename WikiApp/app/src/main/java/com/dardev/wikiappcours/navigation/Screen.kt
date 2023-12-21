package com.dardev.wikiappcours.navigation

sealed class Screen(val route:String) {

    object Splash: Screen("splash_screen")
    object Welcome: Screen("welcome_screen")
    object Home: Screen("home_screen")
    object Details:Screen("details_screen/{characterId}"){
        fun passCharacterId(characterId:String):String{
            return "details_screen/$characterId"
        }
    }
    object Search:Screen("search_screen")
}