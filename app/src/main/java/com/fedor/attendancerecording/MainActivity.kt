package com.fedor.attendancerecording

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fedor.attendancerecording.view.Calendar
import com.fedor.attendancerecording.view.Markers
import com.fedor.attendancerecording.view.NonWorkingDays
import com.fedor.attendancerecording.view.Recording

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

            }
            attendanceRecordingApp()
        }
    }
}

