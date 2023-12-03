package com.fedor.attendancerecording

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
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
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fedor.attendancerecording.view.Calendar
import com.fedor.attendancerecording.view.Markers
import com.fedor.attendancerecording.view.NonWorkingDays
import com.fedor.attendancerecording.view.Recording

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun attendanceRecordingApp(){
    val navController: NavHostController = rememberNavController()
    val navScreens: List<Screens> = listOf(Screens.CALENDAR, Screens.MARKERS, Screens.WORKDAYS)
    var selectedScreen: Screens by remember { mutableStateOf(Screens.CALENDAR) }

    MaterialTheme{
        Surface{
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                            Row(){
                                navScreens.forEach { value ->
                                    IconButton(onClick = { selectedScreen = value; navController.navigate(value.route) }) {
                                        Icon(value.icon, contentDescription = null)
                                    }
                                }
                            }
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)) {
                                    Text(text = "$selectedScreen")
                                }
                        }
                    })
                }
            ){ paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    navigation(navController = navController)
                }
            }
        }
    }
}


@Composable
internal fun navigation(navController: NavHostController) {


    NavHost(navController = navController, startDestination = Screens.CALENDAR.route) {
        composable(route = Screens.CALENDAR.route) {
            Calendar(navController = navController)
        }
        composable(route = Screens.RECORDING.route
            , arguments = listOf(navArgument("selected_date") {type = NavType.StringType})) { backStackEntry ->
            Recording(navController = navController, backStackEntry.arguments?.getString("selected_date"))
        }
        composable(route = Screens.MARKERS.route) {
            Markers()
        }
        composable(route = Screens.WORKDAYS.route) {
            NonWorkingDays()
        }
    }
}

internal enum class Screens(val route: String, name: String = "no", val icon: ImageVector = Icons.Default.Info) {
    CALENDAR(route = "calendar", name = "Календарь", icon = Icons.Default.DateRange),
    RECORDING(route = "recording/{selected_date}"),
    MARKERS(route = "markers", name = "Маркеры", icon = Icons.Default.Star),
    WORKDAYS(route = "workdays", name = "Рабочие дни", icon = Icons.Default.DateRange)
}