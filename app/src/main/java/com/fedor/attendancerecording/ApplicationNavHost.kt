package com.fedor.attendancerecording

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.fedor.attendancerecording.view.screens.EditMarker
import com.fedor.attendancerecording.view.screens.EditStudent
import com.fedor.attendancerecording.view.screens.Export
import com.fedor.attendancerecording.view.screens.MainCalendar
import com.fedor.attendancerecording.view.screens.Markers
import com.fedor.attendancerecording.view.screens.Records
import com.fedor.attendancerecording.view.screens.Schedule
import com.fedor.attendancerecording.view.screens.Settings
import com.fedor.attendancerecording.view.screens.Students

@Composable
internal fun ApplicationNavHost(
    navController: NavHostController,
    startScreen: ApplicationDestination,
    currentScreen: MutableState<ApplicationDestination>
) {

    NavHost(
        navController = navController,
        startDestination = startScreen.route
    ) {
        //main
        composable(route = MainCalendarDestination.route) {
            currentScreen.value = MainCalendarDestination
            MainCalendar(
                onItemClick = { date -> navController.navigateToRecordingByDate(date) }
            )
        }
        composable(
            route = RecordsDestination.routeWithArguments,
            arguments = RecordsDestination.arguments
        ) { navBackStackEntry ->
            currentScreen.value = RecordsDestination
            val date: String? = navBackStackEntry.arguments?.getString(RecordsDestination.navArgumentName)
            Records(date!!)
        }

        //students
        composable(
            route = StudentsDestination.route
        ) {
            currentScreen.value = StudentsDestination
            Students(
                onAddStudentClick = { navController.navigateToEditStudent(0) },
                onEditStudentClick = { idStudent -> navController.navigateToEditStudent(idStudent) }
            )
        }
        composable(
            route = EditStudentDestination.routeWithArguments,
            arguments = EditStudentDestination.arguments
        ) {
            currentScreen.value = EditStudentDestination
            EditStudent(
                onGoBack = { navController.navigateSingleTopToNoState(StudentsDestination.route) }
            )
        }

        //markers
        composable(
            route = MarkersDestination.route
        ) {
            currentScreen.value = MarkersDestination
            Markers(
                onAddMarkerClick = { navController.navigateToEditMarker(0) },
                onEditMarkerClick = { idMarker -> navController.navigateToEditMarker(idMarker) }
            )
        }
        composable(
            route = EditMarkerDestination.routeWithArguments,
            arguments = EditMarkerDestination.arguments
        ) {
            currentScreen.value = EditMarkerDestination
            EditMarker(
                onGoBack = { navController.navigateSingleTopToNoState(MarkersDestination.route) }
            )
        }

        //other
        composable(
            route = ScheduleDestination.route
        ) {
            currentScreen.value = ScheduleDestination
            Schedule()
        }
        composable(
            route = ExportDestination.route
        ) {
            currentScreen.value = ExportDestination
            Export()
        }
        composable(
            route = SettingsDestination.route
        ) {
            currentScreen.value = SettingsDestination
            Settings(
                navScreens =
                settingsNavScreens.map {
                    ApplicationDestinationWithNav(it) { navController.navigateSingleTopTo( it.route ) }
                }
            )
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
data class ApplicationDestinationWithNav(
    val appDestination: ApplicationDestination,
    val navigateToDestination: () -> Unit
)

val settingsNavScreens : List<ApplicationDestination> =
    listOf(MarkersDestination, StudentsDestination)
val appBottomBarScreens: List<ApplicationDestination> =
    listOf(
        MainCalendarDestination,
        ScheduleDestination,
        ExportDestination,
        SettingsDestination)