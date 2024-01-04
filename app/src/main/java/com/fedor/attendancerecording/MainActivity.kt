package com.fedor.attendancerecording

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.fedor.attendancerecording.model.ApplicationContainer
import com.fedor.attendancerecording.model.ApplicationDataContainer

class MainActivity : ComponentActivity() {

    private val permissionList = arrayListOf(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){}
            attendanceRecordingApp()
        }
    }
}