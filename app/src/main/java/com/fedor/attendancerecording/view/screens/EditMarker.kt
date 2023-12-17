package com.fedor.attendancerecording.view.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditMarkerViewModel
import com.fedor.attendancerecording.viewmodel.screens.EditStudentViewModel

@Composable
public fun EditMarker(
    markerId: Int,
    viewModel: EditMarkerViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Text(text = "Ид маркер = ${viewModel.markerId}")
}