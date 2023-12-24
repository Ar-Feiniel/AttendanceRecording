package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.view.components.Calendar
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
        DateLabel(date = dateLabelText)
        Spacer(modifier = Modifier.height(25.dp))
        PairButtonsRow(
            onLeftButtonClick = viewModel::goToPreviousMonth,
            leftButtonText = "<<",
            onRightButtonClick = viewModel::goToNextMonth,
            rightButtonText = ">>",
            buttonHeight = 50.dp,
            buttonWidth = 80.dp
        )
        Spacer(modifier = Modifier.height(25.dp))
        Calendar(calendarItemsUiState, onItemClick = onDayClick)
        Spacer(modifier = Modifier.fillMaxHeight())
    }
}



@Composable
private fun NextPreviousButtons(
    onNextButtonClick: () -> Unit,
    onPreviousButtonClick: () -> Unit
) {
    Row() {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 15.dp), horizontalAlignment = Alignment.Start
        ) {
            CalendarNavigationButton("previous", onClickAction = onPreviousButtonClick)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp), horizontalAlignment = Alignment.End
        ) {
            CalendarNavigationButton("next", onClickAction = onNextButtonClick)
        }
    }
}

@Composable
internal fun CalendarNavigationButton(type: String = "next", onClickAction: () -> Unit) {
    Button(colors = ButtonDefaults.buttonColors(
        containerColor = Color(204, 204, 255), contentColor = Color(0, 75, 150)
    )
        , shape = RoundedCornerShape(20f), modifier = Modifier
            .width(80.dp)
            .height(50.dp)
        , onClick = onClickAction) {
        Text(
            fontSize = 28.sp,
            text = when (type) {
                "previous" -> "<<"
                "next" -> ">>"
                else -> ""
            }
        )
    }
}