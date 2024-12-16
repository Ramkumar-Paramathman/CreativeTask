package com.fullcreative.randomcity.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fullcreative.randomcity.ui.navigation.AppNavHost
import com.fullcreative.randomcity.ui.theme.RandomCityAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomCityAppTheme {
                AppNavHost() // Start the navigation
            }
        }
    }
}
