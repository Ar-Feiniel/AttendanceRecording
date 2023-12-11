package com.fedor.attendancerecording

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fedor.attendancerecording.model.ApplicationContainer
import com.fedor.attendancerecording.model.ApplicationDataContainer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){}
            attendanceRecordingApp()
        }
    }
}