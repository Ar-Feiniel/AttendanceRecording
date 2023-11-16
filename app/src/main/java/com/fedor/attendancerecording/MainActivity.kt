package com.fedor.attendancerecording

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.fedor.attendancerecording.ui.theme.AttendanceRecordingTheme
import com.fedor.attendancerecording.view.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AttendanceRecordingTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigation()
                }
            }
        }
    }
}

