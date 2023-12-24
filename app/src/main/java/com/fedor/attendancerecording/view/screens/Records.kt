package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.view.components.PairButtonsRow

@Composable
fun Records(selectedDate: String)
{
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        DateLabel(date = selectedDate)
        Row( modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = stringResource(id = R.string.pair))
        }
        Spacer(modifier = Modifier.height(12.dp))
        PairTabRow()
        Spacer(modifier = Modifier.height(12.dp))
        StudentsList()
    }
}

@Preview
@Composable
internal fun PairTabRow() {
    var tabIndex: Int by remember { mutableStateOf<Int>(0) }
    var pairs: Int by remember { mutableStateOf<Int>(3) }

    Column {
        val tabRowAction: @Composable () -> Unit = {
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.height(30.dp)
            ) {
                for (i in 1..pairs) {
                    Tab(
                        selected = tabIndex != i - 1,
                        onClick = { tabIndex = i - 1 },
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background((Color(102, 102, 255)))
                                .fillMaxSize()
                        ) {
                            Text(
                                text = "$i",
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                    }
                }
            }
        }

        PairButtonsRow(
            onLeftButtonClick = { if (pairNumberChangesValid(pairs, "-")) pairs-- },
            leftButtonText = "-",
            onRightButtonClick = { if (pairNumberChangesValid(pairs, "+")) pairs++ },
            rightButtonText = "+",
            buttonHeight = 40.dp,
            buttonWidth = 60.dp,
            centralContent = tabRowAction
        )
    }
}

fun pairNumberChangesValid(pairNum: Int, action: String): Boolean {
    return when(action){
        "+" -> pairNum+1 <= 10
        "-" -> pairNum-1 >= 1
        else -> false
    }
}

@Composable
internal fun StudentsList(){
    val students: List<String> = listOf("name surname partonymic1"
        , "name surname partonymi2"
        , "name surname partonymi3"
        , "name surname partonymi4"
        , "name surname partonymi5"
        , "name surname partonymi6"
        , "name surname partonymi7")
    val markers: List<String> = listOf("2у", "2", "1")
    Column() {
        students.forEach {string ->
            Spacer(modifier = Modifier.height(10.dp))
            Row(){
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(text = string)
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                    horizontalAlignment = Alignment.Start,) {
                    MarkersComboBox(markers = markers)
                }
            }
        }
    }
}

@Composable
internal fun MarkersComboBox(markers: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    Button(shape = RoundedCornerShape(10.dp), modifier = Modifier
        .width(100.dp)
        .height(30.dp),
        onClick = { expanded = !expanded }
    ) {
        Text(text = "marker")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        markers.forEach { string ->
            DropdownMenuItem(text = { Text(text = string) },
                onClick = { /*TODO*/ })
        }
    }
}