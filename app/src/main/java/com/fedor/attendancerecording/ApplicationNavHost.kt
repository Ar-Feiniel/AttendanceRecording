package com.fedor.attendancerecording

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fedor.attendancerecording.view.screens.EditMarker
import com.fedor.attendancerecording.view.screens.EditStudent
import com.fedor.attendancerecording.view.screens.Export
import com.fedor.attendancerecording.view.screens.MainCalendar
import com.fedor.attendancerecording.view.screens.Markers
import com.fedor.attendancerecording.view.screens.NonWorkingDays
import com.fedor.attendancerecording.view.screens.Recording
import com.fedor.attendancerecording.view.screens.Settings
import com.fedor.attendancerecording.view.screens.Students

@Composable
internal fun ApplicationNavHost(navController: NavHostController, startScreen: ApplicationDestination) {

    NavHost(navController = navController, startDestination = startScreen.route) {
        composable(route = MainCalendarDestination.route) {
            MainCalendar(onDayClick = { date ->
                navController.navigateToRecordingByDate(date)
            })
        }
        composable(route = RecordingDestination.routeWithArguments,
                arguments = RecordingDestination.arguments) { navBackStackEntry ->
            val date: String? = navBackStackEntry.arguments?.getString(RecordingDestination.navArgumentName)
            Recording(date!!)
        }

        // students action
        composable(route = StudentsDestination.route) {
            Students(onEditStudentClick = { idStudent ->
                navController.navigateToEditStudent(idStudent)
            })
        }
        composable(
            route = EditStudentDestination.routeWithArguments,
            arguments = EditStudentDestination.arguments
        ) { navBackStackEntry ->
            val studentId: Int? =
                navBackStackEntry.arguments?.getInt(EditStudentDestination.navArgumentName)
            EditStudent(studentId!!)
        }

        // marker action (no reaction)
        composable(route = MarkersDestination.route) {
            Markers(onEditMarkerClick = { idMarker ->
                navController.navigateToEditMarker(idMarker)
            })
        }
        composable(
            route = EditMarkerDestination.route,
            arguments = EditMarkerDestination.arguments
        ) { navBackStackEntry ->
            val markerId: Int? =
                navBackStackEntry.arguments?.getInt(EditMarkerDestination.navArgumentName)
            EditMarker(markerId!!)
        }

        // other action
        composable(route = ScheduleDestination.route) {
            NonWorkingDays()
        }
        composable(route = ExportDestination.route) {
            Export()
        }
        composable(route = SettingsDestination.route) {
            Settings()
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String){
    this.navigate(route){
        popUpTo(
            this@navigateSingleTopTo.graph.findStartDestination().id
        ){
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun NavHostController.navigateToEditStudent(studentId: Int){
    this.navigateSingleTopTo("${EditStudentDestination.route}/$studentId")
}
private fun NavHostController.navigateToEditMarker(markerId: Int){
    this.navigateSingleTopTo("${EditMarkerDestination.route}/$markerId")
}
private fun NavHostController.navigateToRecordingByDate(date: String){
    this.navigateSingleTopTo("${RecordingDestination.route}/$date")
}