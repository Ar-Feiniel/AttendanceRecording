package com.fedor.attendancerecording.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.viewmodel.calendar.CalendarItem
import com.fedor.attendancerecording.viewmodel.calendar.CalendarViewModel

@Preview
@Composable
public fun Calendar() {
    val viewModel: CalendarViewModel = CalendarViewModel()
    val calendarList = viewModel.calendarLogic.getMonthList(2023, 12)

    Column(modifier = Modifier.fillMaxWidth()) {
        DateLabel("26.ноябрь.2023")
        Spacer(modifier = Modifier.height(25.dp))
        NextPreviousButtons()
        Spacer(modifier = Modifier.height(50.dp))
        Calendar2(calendarList)
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

@Composable
internal fun Calendar(calendar: Array<Array<CalendarItem?>>) {
    Box(
        contentAlignment = Alignment.Center
        , modifier = Modifier.fillMaxWidth(1f)
    ) {
        Column {
            for (row in calendar) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    for (cell in row) {
                        Column(
                            modifier = Modifier.sizeIn(minHeight = 53.dp, minWidth=53.dp, maxHeight = 53.dp, maxWidth = 53.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box() {
                                if (cell != null) {
                                    Button(
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(153, 153, 255),
                                            contentColor = Color(255, 255, 255)
                                        ),
                                        shape = CircleShape,
                                        border = when (cell.isCurrent) {
                                            true -> BorderStroke(2.dp, Color.Black)
                                            false -> null
                                        },
                                        contentPadding = PaddingValues(0.dp),
                                        onClick = { /*TODO*/ }) {
                                        Text(
                                            text = "${cell.day}",
                                            fontSize = 24.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
internal fun Calendar2(calendar: List<CalendarItem?>) {
    Box(
        contentAlignment = Alignment.Center
        , modifier = Modifier.padding(start = 12.dp, end=12.dp)
    ) {
        LazyVerticalGrid(contentPadding = PaddingValues(0.dp)
            , horizontalArrangement = Arrangement.SpaceAround
            , verticalArrangement = Arrangement.SpaceAround
            , columns = GridCells.Fixed(7)
            , content = {
                items(calendar) {day ->
                    if (day != null) {
                        Button(modifier = Modifier.padding(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(153, 153, 255),
                                contentColor = Color(255, 255, 255)
                            ),
                            shape = CircleShape,
                            border = when (day.isCurrent) {
                                true -> BorderStroke(2.dp, Color.Black)
                                false -> null
                            },
                            contentPadding = PaddingValues(0.dp),
                            onClick = { /*TODO*/ }) {
                            Text(
                                text = "${day.day}",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
        })
    }
}