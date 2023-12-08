package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.view.components.Calendar
import com.fedor.attendancerecording.viewmodel.screens.maincalendar.MainCalendarViewModel

@Preview
@Composable
public fun MainCalendar() {
    val viewModel: MainCalendarViewModel = MainCalendarViewModel()
    val calendarList = viewModel.getMonthList()

    Column(modifier = Modifier.fillMaxWidth()) {
        DateLabel(viewModel.getGracefulDateText())
        Spacer(modifier = Modifier.height(25.dp))
        NextPreviousButtons()
        Spacer(modifier = Modifier.height(50.dp))
        Calendar(calendarList, onItemClick = {})
        Spacer(modifier = Modifier.fillMaxHeight())
    }
}
@Composable
private fun NextPreviousButtons() {
    Row() {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 15.dp), horizontalAlignment = Alignment.Start
        ) {
            CalendarNavigationButton("previous")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 15.dp), horizontalAlignment = Alignment.End
        ) {
            CalendarNavigationButton("next")
        }
    }
}

@Composable
internal fun CalendarNavigationButton(type: String = "next") {
    Button(colors = ButtonDefaults.buttonColors(
        containerColor = Color(204, 204, 255), contentColor = Color(0, 75, 150)
    )
        , shape = RoundedCornerShape(20f), modifier = Modifier
            .width(80.dp)
            .height(50.dp)
        , onClick = { /*TODO*/ }) {
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