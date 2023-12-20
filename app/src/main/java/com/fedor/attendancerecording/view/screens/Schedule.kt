package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fedor.attendancerecording.view.components.Calendar
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.viewmodel.screens.NotWorkingDaysViewModel

@Composable
fun Schedule(){
    val viewModel = NotWorkingDaysViewModel()
    val calendarList = viewModel.getMonthList()

    Column(modifier = Modifier.fillMaxWidth()) {
        DateLabel(viewModel.getGracefulDateText())
        Spacer(modifier = Modifier.height(25.dp))
        Calendar(calendarList, onItemClick = {})
        Spacer(modifier = Modifier.fillMaxHeight())
    }
}