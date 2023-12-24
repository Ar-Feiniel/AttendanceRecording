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
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.entityeditor.Text_EditorComponent
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditStudentViewModel

@Composable
public fun EditStudent(
    onGoBack: () -> Unit,
    viewModel: EditStudentViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Column {
        Text_EditorComponent(
            value = viewModel.studentUiState.studentDetails.surname,
            onValueChange = { viewModel.updateUiState(viewModel.studentUiState.studentDetails.copy(surname = it)) },
            labelStringResId = R.string.surname
        )
        Text_EditorComponent(
            value = viewModel.studentUiState.studentDetails.name,
            onValueChange = { viewModel.updateUiState(viewModel.studentUiState.studentDetails.copy(name = it)) },
            labelStringResId = R.string.name
        )
        Text_EditorComponent(
            value = viewModel.studentUiState.studentDetails.patronymic,
            onValueChange = { viewModel.updateUiState(viewModel.studentUiState.studentDetails.copy(patronymic = it)) },
            labelStringResId = R.string.patronymic
        )
        Button(onClick =   { viewModel.upsertStudent(); onGoBack() }) {
            Text(text = stringResource(id = viewModel.actionNameStringResId))
        }
    }
}