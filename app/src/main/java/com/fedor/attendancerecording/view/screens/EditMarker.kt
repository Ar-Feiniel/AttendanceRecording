package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.entityeditor.Text_EditorComponent
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditMarkerViewModel

@Composable
public fun EditMarker(
    onGoBack: () -> Unit,
    viewModel: EditMarkerViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState by viewModel.uiState.collectAsState()

    var isDropDownExpanded: Boolean by remember { mutableStateOf(false) }

    Column {
        Text_EditorComponent(
            value = uiState.markerDetails.name,
            onValueChange = { viewModel.updateUiState(uiState.markerDetails.copy(name = it)) },
            labelStringResId = R.string.marker
        )
        Box(){
            Button(onClick = { isDropDownExpanded = !isDropDownExpanded }) {
                Text(
                    text = uiState.markerTypesList.find {
                        it.idMarkerType == uiState.markerDetails.idMarkerType
                    }?.name
                        ?: stringResource(id = R.string.select_type)
                )
            }
            Column {
                DropdownMenu(expanded = isDropDownExpanded, onDismissRequest = { isDropDownExpanded = false }) {
                    uiState.markerTypesList.forEach{ item ->
                        DropdownMenuItem(
                            text = { Text(text = item.name) },
                            onClick = {
                                viewModel.updateUiState(uiState.markerDetails.copy(idMarkerType = item.idMarkerType))
                                isDropDownExpanded = !isDropDownExpanded
                            }
                        )
                    }
                }
            }
        }
        Button(onClick =   { viewModel.upsertMarker(); onGoBack() }) {
            Text(text = stringResource(id = viewModel.actionNameStringResId))
        }
    }
}