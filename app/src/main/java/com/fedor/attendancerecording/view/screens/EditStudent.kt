package com.fedor.attendancerecording.view.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditStudentViewModel
import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

@Composable
public fun EditStudent(
    studentId: Int,
    viewModel: EditStudentViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Text(text = "${viewModel.studentId}")
}