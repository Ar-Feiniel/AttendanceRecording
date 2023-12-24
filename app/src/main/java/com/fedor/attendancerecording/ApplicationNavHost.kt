package com.fedor.attendancerecording

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fedor.attendancerecording.view.screens.EditMarker
import com.fedor.attendancerecording.view.screens.EditStudent
import com.fedor.attendancerecording.view.screens.Export
import com.fedor.attendancerecording.view.screens.MainCalendar
import com.fedor.attendancerecording.view.screens.Markers
import com.fedor.attendancerecording.view.screens.Schedule
import com.fedor.attendancerecording.view.screens.Records
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
        composable(route = RecordsDestination.routeWithArguments,
                arguments = RecordsDestination.arguments) { navBackStackEntry ->
            val date: String? = navBackStackEntry.arguments?.getString(RecordsDestination.navArgumentName)
            Records(date!!)
        }

        // students action
        composable(route = StudentsDestination.route) {
            Students(
                onAddStudentClick = { navController.navigateToEditStudent(0) },
                onEditStudentClick = { idStudent -> navController.navigateToEditStudent(idStudent) })
        }
        composable(
            route = EditStudentDestination.routeWithArguments,
            arguments = EditStudentDestination.arguments
        ) {
            // student id in states
            EditStudent( onGoBack = { navController.navigateSingleTopToNoState(StudentsDestination.route) })
        }

        // marker action (no reaction)
        composable(route = MarkersDestination.route) {
            Markers(onAddMarkerClick = { navController.navigateToEditMarker(0) },
                onEditMarkerClick = { idMarker -> navController.navigateToEditMarker(idMarker) })
        }
        composable(
            route = EditMarkerDestination.routeWithArguments,
            arguments = EditMarkerDestination.arguments
        ) {
            // marker id in states
            EditMarker( onGoBack = { navController.navigateSingleTopToNoState(MarkersDestination.route)})
        }

        // other action
        composable(route = ScheduleDestination.route) {
            Schedule()
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

fun NavHostController.navigateSingleTopToNoState(route: String){
    this.navigate(route){
        popUpTo(
            this@navigateSingleTopToNoState.graph.findStartDestination().id
        ){
            saveState = false
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
    this.navigateSingleTopTo("${RecordsDestination.route}/$date")
}