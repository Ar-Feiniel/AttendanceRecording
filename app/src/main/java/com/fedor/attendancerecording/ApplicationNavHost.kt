package com.fedor.attendancerecording

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fedor.attendancerecording.view.screens.Export
import com.fedor.attendancerecording.view.screens.MainCalendar
import com.fedor.attendancerecording.view.screens.Markers
import com.fedor.attendancerecording.view.screens.NonWorkingDays
import com.fedor.attendancerecording.view.screens.Recording
import com.fedor.attendancerecording.view.screens.Settings
import com.fedor.attendancerecording.view.screens.Students

@Composable
internal fun ApplicationNavHost(navController: NavHostController, startScreen: Screens) {

    NavHost(navController = navController, startDestination = startScreen.route) {
        composable(route = Screens.CALENDAR.route) {
            MainCalendar()
        }
        composable(route = Screens.RECORDING.route
            , arguments = listOf(navArgument("selected_date") { type = NavType.StringType })) { backStackEntry ->
            Recording(navController = navController, backStackEntry.arguments?.getString("selected_date"))
        }
        composable(route = Screens.STUDENTS.route) {
            Students()
        }
        composable(route = Screens.MARKERS.route) {
            Markers()
        }
        composable(route = Screens.WORKDAYS.route) {
            NonWorkingDays()
        }
        composable(route = Screens.EXPORT.route) {
            Export()
        }
        composable(route = Screens.SETTINGS.route) {
            Settings()
        }
    }
}