package com.fedor.attendancerecording.view

import android.media.midi.MidiDevice.MidiConnection
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedor.attendancerecording.ui.theme.AttendanceRecordingTheme
import com.fedor.attendancerecording.viewmodel.mainpage.MainSelector
import com.fedor.attendancerecording.view.components.*

@Composable
public fun Calendar() {
    val selector: MainSelector = MainSelector();
    val calendar = selector.getMonthArray(2023, 9)
    AttendanceRecordingTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            BurgerMenuButton()
            Spacer(modifier = Modifier.height(25.dp))
            DateLabel()
            Spacer(modifier = Modifier.height(25.dp))
            NextPreviousButtons()
            Spacer(modifier = Modifier.height(50.dp))
            Calendar(calendar)
        }
    }
}

@Composable
internal fun DateLabel() {
    Row() {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "ноябрь.2023", fontSize = 32.sp
            )
        }
    }
}

@Composable
internal fun NextPreviousButtons() {
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
        containerColor = Color(204, 204, 255), contentColor = Color(0, 75, 150))
        , shape = RoundedCornerShape(20f), modifier = Modifier
            .width(80.dp)
            .height(50.dp)
        , onClick = { /*TODO*/ }) {
        Text(
            fontSize = 28.sp,
            text = when (type) {
                "previous" -> "<<"
                "next" -> ">>"
                else -> "xz"
            }
        )
    }
}

@Composable
internal fun Calendar(calendar: Array<Array<Int>>) {
    AttendanceRecordingTheme {
        Box(
            contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                for (row in calendar) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        for (cell in row) {
                            Column(
                                modifier = Modifier.defaultMinSize(53.dp, 53.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (cell != -1) {
                                    Button(colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(153, 153, 255),
                                        contentColor = Color(255, 255, 255)
                                    ),
                                        shape = RoundedCornerShape(100.dp),
                                        contentPadding = PaddingValues(0.dp),
                                        modifier = Modifier
                                            .height(45.dp)
                                            .width(45.dp),
                                        onClick = { /*TODO*/ }) {
                                        Text(
                                            text = "$cell",
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