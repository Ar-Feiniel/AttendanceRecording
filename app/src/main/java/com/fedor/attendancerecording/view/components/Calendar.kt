package com.fedor.attendancerecording.view.components


import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedor.attendancerecording.viewmodel.screens.maincalendar.CalendarItem
import java.util.Date

@Composable
internal fun Calendar(calendar: List<CalendarItem?>,
                      onItemClick: (date: Date) -> Unit) {
    Box(
        contentAlignment = Alignment.Center
        , modifier = Modifier.padding(start = 12.dp, end=12.dp)
    ) {
        LazyVerticalGrid(contentPadding = PaddingValues(0.dp)
            , horizontalArrangement = Arrangement.SpaceAround
            , verticalArrangement = Arrangement.SpaceAround
            , columns = GridCells.Fixed(7)
            , content = {
                items(calendar) {item ->
                    if (item != null) {
                        Button(modifier = Modifier.padding(3.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(153, 153, 255),
                                contentColor = Color(255, 255, 255)
                            ),
                            shape = CircleShape,
                            border = when (item.isCurrent) {
                                true -> BorderStroke(2.dp, Color.Black)
                                false -> null
                            },
                            contentPadding = PaddingValues(0.dp),
                            onClick = { onItemClick(item.date) }) {
                            Text(
                                text = "${item.day}",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            })
    }
}