package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.entity.MarkerType
import com.fedor.attendancerecording.view.components.ComboBoxItem
import com.fedor.attendancerecording.view.components.DropDownComboBox
import com.fedor.attendancerecording.view.components.entityeditor.Text_EditorComponent
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.EditMarkerViewModel

@Composable
public fun EditMarker(
    onGoBack: () -> Unit,
    viewModel: EditMarkerViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    var selectedType: MutableState<String> = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(start = 4.dp, end = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text_EditorComponent(
            value = uiState.markerDetails.name,
            onValueChange = { viewModel.updateUiState(uiState.markerDetails.copy(name = it)) },
            labelStringResId = R.string.marker
        )
        selectedType.value = uiState.markerTypesList.find { it.idMarkerType == uiState.markerDetails.idMarkerType }?.name
            ?: stringResource(id = R.string.select_type)
        Box() {
            DropDownComboBox(
                onSelectedItemChanged = {
                    viewModel.updateUiState(uiState.markerDetails.copy(idMarkerType = it.itemObject.idMarkerType))
                },
                selectedItem = selectedType,
                itemsList = uiState.markerTypesList.map { it -> it.toComboboxItem() },
                modifier = Modifier.width(150.dp)
            )
        }
        Button(onClick = { viewModel.upsertMarker(); onGoBack() }) {
            Text(text = stringResource(id = viewModel.actionNameStringResId))
        }
    }
}

fun MarkerType.toComboboxItem(): ComboBoxItem<MarkerType> = ComboBoxItem(
    itemObject = this,
    visibleText = this.name
)