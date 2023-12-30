package com.fedor.attendancerecording.view.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.seed
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.view.components.ComboBoxItem
import com.fedor.attendancerecording.view.components.DateLabel
import com.fedor.attendancerecording.view.components.DropDownComboBox
import com.fedor.attendancerecording.view.components.PairButtonsRow
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.RecordsViewModel

@Composable
fun Records(
    selectedDate: String,
    viewModel: RecordsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    var pairNumIndex: MutableState<Int> = remember { mutableStateOf<Int>(0) }
    val uiState by viewModel.uiState.collectAsState()

    val defaultPairNum = when(uiState.recordsDetails.any { it.date == selectedDate }){
        true -> uiState.recordsDetails.filter{ it.date == selectedDate}.maxOf { it.pairNum }
        false -> 3
    }

    Log.i("Records", "collect markers ${uiState.markers.toString()}")
    Log.i("Records", "pairNum $defaultPairNum")

    Column {
        Spacer(modifier = Modifier.height(12.dp))
        DateLabel(date = selectedDate)
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = stringResource(id = R.string.pair))
        }
        Spacer(modifier = Modifier.height(12.dp))
        PairTabRow(
            pairNumIndex = pairNumIndex,
            defaultPairNum = defaultPairNum
        )
        Spacer(modifier = Modifier.height(12.dp))
        StudentsList(
            students = uiState.students,
            markers = uiState.markers,
            records = uiState.recordsDetails,
            upsertRecord = viewModel::upsertRecord,
            date = viewModel.dateString,
            pairNumIndex = pairNumIndex.value,
            refreshDetails = viewModel::refreshRecordDetails
        )
    }
}

@Composable
internal fun PairTabRow(
    pairNumIndex: MutableState<Int>,
    defaultPairNum: Int
) {
    var tabIndex: Int by remember { mutableStateOf<Int>(0) }
    var pairs: Int by rememberSaveable(defaultPairNum) { mutableStateOf<Int>(defaultPairNum) }

        val tabRowAction: @Composable () -> Unit = {
                TabRow(
                    modifier = Modifier
                        .clip(RoundedCornerShape(5.dp)),
                    selectedTabIndex = pairNumIndex.value,
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.surface,
                    indicator = {}
                ) {
                    for (i in 1..pairs) {
                        Tab(
                            selected = pairNumIndex.value != i - 1,
                            onClick = { pairNumIndex.value = i - 1 },
                            modifier = Modifier
                                .padding(vertical = 4.dp, horizontal = 1.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .padding(1.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .background(
                                        if (pairNumIndex.value + 1 == i)
                                            MaterialTheme.colorScheme.primary
                                        else
                                            MaterialTheme.colorScheme.inversePrimary
                                    )
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "$i",
                                    maxLines = 1,
                                    color =
                                        if (pairNumIndex.value + 1 == i)
                                            MaterialTheme.colorScheme.inversePrimary
                                        else
                                            MaterialTheme.colorScheme.primary,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
            }
        }

        PairButtonsRow(
            onLeftButtonClick = { if (pairNumberChangesValid(pairs, tabIndex, "-")) pairs--},
            leftButtonText = "-",
            onRightButtonClick = { if (pairNumberChangesValid(pairs, tabIndex, "+")) pairs++},
            rightButtonText = "+",
            buttonHeight = 40.dp,
            buttonWidth = 60.dp,
            centralContent = tabRowAction
        )
}

fun pairNumberChangesValid(pairNum: Int, index: Int, action: String): Boolean {
    val maxPairNum = 10
    val minPairNum = 1

    return when(action) {
        "+" -> pairNum+1 <= maxPairNum
        "-" -> pairNum-1 >= minPairNum && index+1 <= pairNum-1
        else -> false
    }
}

@Composable
internal fun StudentsList(
    students: List<Student>,
    markers: List<Marker>,
    records: List<RecordsViewModel.RecordDetails>,
    upsertRecord: (item: RecordsViewModel.RecordDetails) -> Unit,
    date: String = "",
    pairNumIndex: Int = 0,
    refreshDetails: () -> Unit
) {
    refreshDetails()
    Log.i("Records", "students records  ${students.toString()}")
    Log.i("Records", "Listing markers  ${markers.toString()}")
    Log.i("Records", "Listing records  ${records.toString()}")

    Column() {
        students.forEach { item ->
            Log.i("Records", "List recomposing")
            // create a temporary record if the student does not have an record
            val conformityRecord: RecordsViewModel.RecordDetails =
                records.find { it.idStudent == item.idStudent && it.date == date && it.pairNum == pairNumIndex + 1 }
                    ?: RecordsViewModel.RecordDetails(
                        idStudent = item.idStudent,
                        date = date,
                        pairNum = pairNumIndex + 1
                    )

            Log.i("Records", "conformityRecord ${conformityRecord.toString()}")

            val selectedMarker = rememberSaveable(conformityRecord) {
                mutableStateOf(
                    if (conformityRecord.idMarker == 0) "+"
                    else markers.find { conformityRecord.idMarker == it.idMarker }?.name
                        ?: "not found"
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row() {
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(
                        text = "${item.name} ${item.surname} ${item.patronymic}",
                        overflow = TextOverflow.Clip
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center),
                    horizontalAlignment = Alignment.Start
                ) {
                    DropDownComboBox<Marker>(
                        onSelectedItemChanged = {
                            conformityRecord.idMarker =
                                markers.find { it.name == selectedMarker.value }?.idMarker ?: 0
                            upsertRecord(conformityRecord)
                        },
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