package com.fedor.attendancerecording.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
    val markerTypesUiState by viewModel.markersTypeUiState.collectAsState()

    var expanded: Boolean by remember { mutableStateOf(false) }

    Column {
        Text_EditorComponent(
            value = viewModel.markerUiState.markerDetails.name,
            onValueChange = { viewModel.updateUiState(viewModel.markerUiState.markerDetails.copy(name = it)) },
            labelStringResId = R.string.marker
        )
        Box(){
            Button(onClick = { expanded = !expanded }) {
                Text(text = viewModel.markerUiState.markerDetails.markerTypeName)
            }
            Column {
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    markerTypesUiState.markerTypesList.forEach{ item ->
                        DropdownMenuItem(text = { Text(text = item.name) },
                            onClick = {
                                viewModel.updateUiState(viewModel.markerUiState.markerDetails.copy(markerTypeName = item.name))
                                expanded = !expanded
                            }
                        )
                    }
                }
            }
        }
        Log.i("EditMarker", "idMarkerTypes= ${markerTypesUiState.markerTypesList.toString()}")
        Log.i("EditMarker", "SelectedMarkerTypeName = ${viewModel.markerUiState.markerDetails.markerTypeName}")
        Button(onClick =   { viewModel.upsertMarker(); onGoBack() }) {
            Text(text = stringResource(id = viewModel.actionNameStringResId))
        }
    }
}