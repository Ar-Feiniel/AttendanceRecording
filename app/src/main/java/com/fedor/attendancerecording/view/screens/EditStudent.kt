package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditStudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun EditStudent(
    studentId: Int,
    viewModel: EditStudentViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Column {
        Text(text = "Ид студент = ${viewModel.studentId}")
        TextField(
            value = viewModel.studentUiState.studentDetails.name,
            onValueChange = { viewModel.updateUiState(viewModel.studentUiState.studentDetails.copy(name = it)) },
            label = { Text(text = "field") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        TextField(
            value = viewModel.studentUiState.studentDetails.surname,
            onValueChange = { viewModel.updateUiState(viewModel.studentUiState.studentDetails.copy(surname = it)) },
            label = { Text(text = "field") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        TextField(
            value = viewModel.studentUiState.studentDetails.patronymic,
            onValueChange = { viewModel.updateUiState(viewModel.studentUiState.studentDetails.copy(patronymic = it)) },
            label = { Text(text = "field") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        Button(onClick = viewModel::upsertStudent) {
            Text(text = stringResource(id = viewModel.actionNameStringResId))
        }
    }
}