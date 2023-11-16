package com.fedor.attendancerecording

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
public fun navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.RECORDING.route) {
        composable(route = "calendar") {
            com.fedor.attendancerecording.view.Calendar()
        }
        composable(route = "recording") {
            com.fedor.attendancerecording.view.Recording()
        }
        composable(route = "markers") {
            com.fedor.attendancerecording.view.Calendar()
        }
        composable(route = "workdays") {
            com.fedor.attendancerecording.view.Calendar()
        }
    }
}

internal enum class Screens(val route: String) {
    CALENDAR(route = "calendar"),
    RECORDING(route = "recording"),
    MARKERS(route = "markers"),
    WORKDAYS(route = "workdays")
}