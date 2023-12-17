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
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.view.screens.Export

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun attendanceRecordingApp(){
    //val startScreen: Screens = Screens.CALENDAR
    val startScreen: ApplicationDestination = MainCalendarDestination

    val navController: NavHostController = rememberNavController()
//    val navScreens: List<Screens> = listOf(Screens.CALENDAR, Screens.WORKDAYS,
//                                            Screens.MARKERS, Screens.STUDENTS,
//                                            Screens.EXPORT, Screens.SETTINGS)

    val navScreens: List<ApplicationDestination> = listOf(MainCalendarDestination, ScheduleDestination,
                                                            MarkersDestination, StudentsDestination,
                                                            ExportDestination, SettingsDestination)

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
                                Icon(imageVector = ImageVector.vectorResource(value.iconResId!!),
                                    contentDescription = null,
                                    modifier = Modifier.height(42.dp).width(42.dp)
                                )
                            }
                        }
                    }
                }
            ){ paddingValues ->
                Column(modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.Center) {
                    ApplicationNavHost(navController = navController, startScreen = startScreen)
                }
            }
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
                .width(width) ) }),
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
        , screenNameResId = R.string.schedule
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
    SETTINGS(route = "settings"
        , screenNameResId = R.string.settings
        , icon = { height, width -> Icon( ImageVector.vectorResource(R.drawable.settings), null,
            Modifier
                .height(height)
                .width(width)) })
}