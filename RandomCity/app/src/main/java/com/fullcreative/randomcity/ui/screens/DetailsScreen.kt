package com.fullcreative.randomcity.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.fullcreative.randomcity.worker.CityToastWorker
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(city: String, colorHex: String) {
    
    val cityCoordinates = getCityCoordinates(city)
    //val color = Color(android.graphics.Color.parseColor(colorHex)) // Convert hex to Color

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cityCoordinates, 2f)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(city) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red // Set your background color here
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = city,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Spacer(modifier = Modifier.height(16.dp))
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = cityCoordinates),
                    title = city
                )
            }
        }
    }
}

fun getCityCoordinates(city: String): LatLng {
    return when (city) {
        "Gdańsk" -> LatLng(54.352, 18.646)
        "Warszawa" -> LatLng(52.229, 21.012)
        "Poznań" -> LatLng(52.406, 16.925)
        "Białystok" -> LatLng(53.132, 23.168)
        "Wrocław" -> LatLng(51.107, 17.038)
        "Katowice" -> LatLng(50.259, 19.021)
        "Kraków" -> LatLng(50.064, 19.945)
        else -> LatLng(0.0, 0.0)
    }
}
