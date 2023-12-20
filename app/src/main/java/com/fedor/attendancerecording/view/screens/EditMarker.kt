package com.fedor.attendancerecording.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditMarkerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
public fun EditMarker(
    markerId: Int,
    onGoBack: () -> Unit,
    viewModel: EditMarkerViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Log.i("EditMarker", "Recomposing main")
    var selectedType: String by remember { mutableStateOf(viewModel.markerUiState.markerDetails.markerType) }
    var expanded: Boolean by remember { mutableStateOf(false) }
    Log.i("EditMarker", "ready states")
    Column {
        Text(text = "Ид студент = ${viewModel.markerId}")
        TextField(
            value = viewModel.markerUiState.markerDetails.name,
            onValueChange = { viewModel.updateUiState(viewModel.markerUiState.markerDetails.copy(name = it)) },
            label = { Text(text = "field") },
            modifier = Modifier.fillMaxWidth(),
            enabled = true,
            singleLine = true
        )
        Text(text = selectedType)
        Button(onClick = { expanded = !expanded }) {
            Column {

                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    viewModel.markersTypeList.forEach{ item ->
                        DropdownMenuItem(text = { Text(text = item.name) }, onClick = { selectedType = item.name; expanded = !expanded })
                    }
                }
            }
        }


        Button(onClick =   { /*viewModel.upsertItem();*/ onGoBack() }) {
            Text(text = stringResource(id = viewModel.actionNameStringResId))
        }
    }
}