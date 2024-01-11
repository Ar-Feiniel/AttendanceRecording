package com.fedor.attendancerecording.view.components


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedor.attendancerecording.viewmodel.CalendarItem
import com.fedor.attendancerecording.viewmodel.toConstructorString

@Composable
internal fun Calendar(
    calendar: List<CalendarItem?>,
    onItemClick: (date: String) -> Unit
) {
    Log.i("", calendar.map{ it -> it.toConstructorString() }.toString() )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(start = 12.dp, end = 12.dp)
    ) {
        LazyVerticalGrid(
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.SpaceAround,
            columns = GridCells.Fixed(count = 7),
            content = {
                items(calendar) { item ->
                    if (item != null) {
                        Button(modifier = Modifier.padding(3.dp),
                            shape = CircleShape,
                            colors =
                            ButtonDefaults.buttonColors(
                                containerColor =
                                if (item.isCurrent)
                                    MaterialTheme.colorScheme.tertiary
                                else if (!item.isWorkingDay)
                                    MaterialTheme.colorScheme.error
                                else
                                    MaterialTheme.colorScheme.primary
                            ),
                            contentPadding = PaddingValues(0.dp),
                            onClick = { onItemClick(item.dateString) }
                        ) {
                            Text(
                                text = "${item.dayString}",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            })
    }
}

@Preview
@Composable
private fun Calendar_Preview(){
    Calendar(
        calendar = listOf(
            CalendarItem(year = 2024, month = 1, day = 1, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 2, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 3, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 4, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 5, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 6, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 7, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 8, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 9, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 10, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 11, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 12, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 13, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 14, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 15, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 16, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 17, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 18, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 19, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 20, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 21, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 22, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 23, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 24, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 25, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 26, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 27, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 28, isWorkingDay = false),
            CalendarItem(year = 2024, month = 1, day = 29, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 30, isWorkingDay = true),
            CalendarItem(year = 2024, month = 1, day = 31, isWorkingDay = true),
            null, null, null, null),
        onItemClick = {}
    )
}