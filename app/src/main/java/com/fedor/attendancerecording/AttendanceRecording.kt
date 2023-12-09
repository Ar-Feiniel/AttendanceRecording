package com.fedor.attendancerecording

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.view.screens.Export
import com.fedor.attendancerecording.view.screens.MainCalendar
import com.fedor.attendancerecording.view.screens.Markers
import com.fedor.attendancerecording.view.screens.NonWorkingDays
import com.fedor.attendancerecording.view.screens.Recording
import com.fedor.attendancerecording.view.screens.Settings
import com.fedor.attendancerecording.view.screens.Students

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun attendanceRecordingApp(){
    val startScreen: Screens = Screens.CALENDAR

    val navController: NavHostController = rememberNavController()
    val navScreens: List<Screens> = listOf(Screens.CALENDAR, Screens.WORKDAYS,
                                            Screens.MARKERS, Screens.STUDENTS,
                                            Screens.EXPORT, Screens.SETTINGS)
    var selectedScreen: @Composable () -> Unit by remember { mutableStateOf( { Text( text = stringResource(startScreen.screenNameResId)) }) }

    MaterialTheme{
        Surface{
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                                selectedScreen()
                            }
                    }, modifier = Modifier.padding(5.dp))
                },
                bottomBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                        , horizontalArrangement = Arrangement.SpaceEvenly
                        , verticalAlignment = Alignment.CenterVertically
                    ){
                        navScreens.forEach { value ->
                            IconButton(onClick = {  selectedScreen = { Text(text = stringResource(value.screenNameResId))  }
                                                    navController.navigate(value.route) }) {
                                value.icon(height = 42.dp, width = 42.dp)
                            }
                        }
                    }
                }
            ){ paddingValues ->
                Column(modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.Center) {
                    navigation(navController = navController, startScreen = startScreen)
                }
            }
        }
    }
}
@Composable
internal fun navigation(navController: NavHostController, startScreen: Screens) {

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

internal enum class Screens(val route: String
                            , @StringRes val screenNameResId: Int = 0
                            , val icon: @Composable (height: Dp, width: Dp) -> Unit = { height, width -> Icon( Icons.Default.Info,  null,
        Modifier
            .height(height)
            .width(width))}) {
    CALENDAR(route = "calendar"
        , screenNameResId = R.string.calendar
        , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.calendar_month), null,
            Modifier
                .height(height = height)
                .width(width), ) }),
    RECORDING(route = "recording/{selected_date}"),
    STUDENTS(route = "students"
        , screenNameResId = R.string.students
        , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.group), null,
            Modifier
                .height(height)
                .width(width)) }),
    MARKERS(route = "markers"
        , screenNameResId = R.string.markers
        , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.marker), null,
            Modifier
                .height(height)
                .width(width)) }),
    WORKDAYS(route = "workdays"
        , screenNameResId = R.string.working_days
        , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.edit_calendar), null,
            Modifier
                .height(height)
                .width(width)) }),
    EXPORT(route = "export"
            , screenNameResId = R.string.export
            , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.export), null,
            Modifier
                .height(height)
                .width(width)) }),
    SETTINGS(route = "export"
        , screenNameResId = R.string.settings
        , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.settings), null,
            Modifier
                .height(height)
                .width(width)) })
}