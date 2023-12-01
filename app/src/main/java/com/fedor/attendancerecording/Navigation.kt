package com.fedor.attendancerecording

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
public fun navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.CALENDAR.route) {
        composable(route = Screens.CALENDAR.route) {
            com.fedor.attendancerecording.view.Calendar()
        }
        composable(route = Screens.RECORDING.route
            , arguments = listOf(navArgument("selected_date") {type = NavType.Companion.StringType})) {backStackEntry ->
            com.fedor.attendancerecording.view.Recording(navController = navController, backStackEntry.arguments?.getString("selected_date"))
        }
        composable(route = Screens.MARKERS.route) {
            com.fedor.attendancerecording.view.Markers()
        }
        composable(route = Screens.WORKDAYS.route) {
            com.fedor.attendancerecording.view.NonWorkingDays()
        }
    }
}

internal enum class Screens(val route: String) {
    CALENDAR(route = "calendar"),
    RECORDING(route = "recording/{selected_date}"),
    MARKERS(route = "markers"),
    WORKDAYS(route = "workdays")
}