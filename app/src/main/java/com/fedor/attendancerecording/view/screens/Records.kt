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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.view.components.ComboBoxItem
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.view.components.DropDownComboBox
import com.fedor.attendancerecording.view.components.PairButtonsRow
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.MarkersViewModel
import com.fedor.attendancerecording.viewmodel.screens.RecordsViewModel

@Composable
fun Records(selectedDate: String,
            viewModel: RecordsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val recordsUiState by viewModel.recordsUiState.collectAsState()
    val studentsUiState by viewModel.studentUiState.collectAsState()
    val markersUiState by viewModel.markersUiState.collectAsState()

    Column {
        Spacer(modifier = Modifier.height(12.dp))
        DateLabel(date = selectedDate)
        Row( modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = stringResource(id = R.string.pair))
        }
        Spacer(modifier = Modifier.height(12.dp))
        PairTabRow()
        Spacer(modifier = Modifier.height(12.dp))
        StudentsList(
            students = studentsUiState.studentsList,
            markers = markersUiState.markersList)
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
internal fun StudentsList(
    students: List<Student>,
    markers: List<Marker>
){
    Column() {
        students.forEach { item ->
            val selectedMarker = remember { mutableStateOf("+") }

            Spacer(modifier = Modifier.height(10.dp))
            Row(){
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(text = "${item.name} ${item.surname} ${item.patronymic}", overflow = TextOverflow.Clip)
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                    horizontalAlignment = Alignment.Start,) {
                    DropDownComboBox<Marker>(
                        selectedItem = selectedMarker,
                        itemsList = markers.map { it.toComboBoxItem() }
                    )
                }
            }
        }
    }
}

fun Marker.toComboBoxItem(): ComboBoxItem<Marker> = ComboBoxItem(
    visibleText = this.name,
    itemObject = this
)

fun ComboBoxItem<Marker>.toMarker(): Marker = this.itemObject

@Composable
internal fun MarkersComboBox(markers: List<Marker>) {
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
        markers.forEach { item ->
            DropdownMenuItem(text = { Text(text = item.name) },
                onClick = { /*TODO*/ })
        }
    }
}