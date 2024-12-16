package com.fullcreative.randomcity.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fullcreative.randomcity.ui.screens.DetailScreen
import com.fullcreative.randomcity.ui.screens.MainScreen
import com.fullcreative.randomcity.ui.screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToMain = {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Main.route) {
            MainScreen(LocalContext.current, navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("cityName") { type = NavType.StringType },
                navArgument("colorHex") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName") ?: ""
            val colorHex = backStackEntry.arguments?.getString("colorHex") ?: "#FFFFFF"
            DetailScreen(city = cityName, colorHex = colorHex)
        }
    }
}
