package com.fullcreative.randomcity.ui.navigation


sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Details : Screen("details/{cityName}/{colorHex}") {
        fun createRoute(cityName: String, colorHex: String): String = "details/$cityName/$colorHex"
    }
}
