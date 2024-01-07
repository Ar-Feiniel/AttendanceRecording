package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.view.components.Calendar
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.ScheduleViewModel

@Composable
fun Schedule(
    viewModel: ScheduleViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Column(modifier = Modifier.fillMaxWidth()) {
        DateLabel(viewModel.getGracefulDateText())
        Spacer(modifier = Modifier.height(25.dp))
        Calendar(viewModel.getMonthList(), onItemClick = {})
    }
}