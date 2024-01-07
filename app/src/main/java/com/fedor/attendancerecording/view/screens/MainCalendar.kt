package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.view.components.Calendar
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.view.components.PairButtonsRow
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.MainCalendarViewModel

@Composable
public fun MainCalendar(
    onDayClick: (date: String) -> Unit,
    viewModel: MainCalendarViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val calendarItemsUiState = viewModel.monthListUiState
    val dateLabelText = viewModel.dateLabelText
    Column(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(60.dp))
        PairButtonsRow(
            onLeftButtonClick = viewModel::goToPreviousMonth,
            leftButtonText = "<",
            onRightButtonClick = viewModel::goToNextMonth,
            rightButtonText = ">",
            buttonHeight = 55.dp,
            buttonWidth = 80.dp,
            centralContent = { DateLabel(date = dateLabelText) }
        )
        Spacer(modifier = Modifier.height(25.dp))
        Calendar(calendarItemsUiState, onItemClick = onDayClick)
        Spacer(modifier = Modifier.fillMaxHeight())
    }
}