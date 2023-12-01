package com.fedor.attendancerecording

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
public fun attendanceRecordingApp(){
    MaterialTheme{
        Surface{
            navigation()
        }
    }
}