package com.fedor.attendancerecording.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.entity.ScheduleDay
import com.fedor.attendancerecording.view.components.Calendar
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.ScheduleViewModel

@Composable
fun Schedule(
    viewModel: ScheduleViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val uiState by viewModel.uiState.collectAsState()
    viewModel.holidaysDates = uiState.scheduleRecordsList.filter{ !it.isWorkingDay }.map { it -> it.date }

    Log.i("", uiState.scheduleRecordsList.filter{ !it.isWorkingDay }.map { it -> it.date }.toString())

    var isScheduleDialogOpen by remember{ mutableStateOf(false) }
    val selectedDate = remember{ mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        DateLabel(viewModel.getGracefulSelectedMonthYearText())
        Spacer(modifier = Modifier.height(25.dp))
        if(viewModel.holidaysDates.isNotEmpty()){
            Calendar(
                viewModel.getMonthList(),
                onItemClick = {
                        date -> selectedDate.value = date
                    isScheduleDialogOpen = !isScheduleDialogOpen
                }
            )
        }
    }
    if(isScheduleDialogOpen) {
        ScheduleDialog(
            viewModel = viewModel,
            selectedDate = selectedDate,
            onConfirmClick = { value: Boolean ->
                viewModel.upsertSchedule(
                    ScheduleDay(
                        idDay = uiState.scheduleRecordsList.find { it.date == selectedDate.value }?.idDay ?: 0,
                        date = selectedDate.value,
                        isWorkingDay = value
                    )
                )
                isScheduleDialogOpen = !isScheduleDialogOpen
            },
            onCancelClick = { isScheduleDialogOpen = !isScheduleDialogOpen }
        )
    }
}

@Composable
fun ScheduleDialog(
    viewModel: ScheduleViewModel,
    selectedDate: MutableState<String>,
    onConfirmClick: (value: Boolean) -> Unit,
    onCancelClick: () -> Unit
){
    val selectedType =
        remember {
        mutableStateOf(
            !viewModel.holidaysDates.contains(selectedDate.value)
        )
    }
    var isDropDownExpanded: Boolean by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onCancelClick,
        title = {
            Row(){
                Text(text = stringResource(id = R.string.holiday_status_change))
            }
        },
        text = {
            Column {
                Text(text = selectedDate.value)
                Button(onClick = { isDropDownExpanded = !isDropDownExpanded }) {
                    Text(
                        text = stringResource(
                            id = viewModel.scheduleDayTypes.entries.find { it.key == selectedType.value }?.value!!
                        )
                    )
                }
                DropdownMenu(
                    expanded = isDropDownExpanded,
                    onDismissRequest = { isDropDownExpanded = false }
                ) {
                    viewModel.scheduleDayTypes.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = stringResource(id = item.value)) },
                            onClick = {
                                selectedType.value = item.key
                                isDropDownExpanded = !isDropDownExpanded
                            }
                        )
                    }
                }
            }
        },
        confirmButton = {
            IconButton(onClick = { onConfirmClick(selectedType.value) }) {
                Icon(ImageVector.vectorResource(id = R.drawable.check,), contentDescription = "enter")
            }
        },
        dismissButton = {
            IconButton(onClick = onCancelClick) {
                Icon(ImageVector.vectorResource(id = R.drawable.close,), contentDescription = "close")
            }
        },
    )
}