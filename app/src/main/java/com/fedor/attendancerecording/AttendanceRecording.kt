package com.fedor.attendancerecording

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun attendanceRecordingApp(){
    val startScreen: ApplicationDestination = MainCalendarDestination

    val navController: NavHostController = rememberNavController()

    val bottomBarNavScreens: List<ApplicationDestination> = listOf(MainCalendarDestination, ScheduleDestination,
                                                            MarkersDestination, StudentsDestination,
                                                            ExportDestination, SettingsDestination)

    var selectedScreen: @Composable () -> Unit by remember { mutableStateOf( { Text( text = stringResource(startScreen.screenNameResId)) }) }

    AppTheme{
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
                        bottomBarNavScreens.forEach { value ->
                            IconButton(onClick = {  selectedScreen = { Text(text = stringResource(value.screenNameResId))  }
                                                    navController.navigateSingleTopTo(value.route) }) {
                                Icon(imageVector = ImageVector.vectorResource(value.iconResId!!),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(42.dp)
                                        .width(42.dp)
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