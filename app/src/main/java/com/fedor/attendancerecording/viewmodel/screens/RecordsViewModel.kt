package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.RecordsDestination
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.entity.Schedule
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerTypeRepository
import com.fedor.attendancerecording.model.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecordsViewModel(
    savedStateHandle: SavedStateHandle,
    private val recordRepository: RecordRepository,
    private val studentRepository: StudentRepository,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository
    //private val scheduleRepository: ScheduleRepository
) : ViewModel() {
    private val _dateString: String = checkNotNull(savedStateHandle[RecordsDestination.navArgumentName])

    private val _uiState: MutableStateFlow<RecordsScreenUiState> = MutableStateFlow(RecordsScreenUiState())
    val uiState: StateFlow<RecordsScreenUiState> = _uiState.asStateFlow()
    val dateString: String
        get() {
//            val dateParts: Array<Int> = arrayOf()
//            for ((index, item) in _dateString.split(".").iterator().withIndex()){
//                dateParts[index] = item.toInt()
//            }
            val dateParts: List<Int> = _dateString.split(".").map { it -> it.toInt() }
            val day =
                when (dateParts[0] in 1..9) {
                    true -> "0${dateParts[0]}"
                    false -> "${dateParts[0]}"
                }
            val month =
                when (dateParts[1] in 1..9) {
                    true -> "0${dateParts[1]}"
                    false -> "${dateParts[1]}"
                }
            val year = dateParts[2]
            return "${day}.${month}.${year}"
        }

    init {
        refreshUiState()
    }

    private fun refreshUiState(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    recordsDetails = recordRepository.getAllDataList().map { it.toRecordDetails() },
                    students = studentRepository.getAllDataList(),
                    markers = markerRepository.getAllDataList(),
                    markerTypes = markerTypeRepository.getAllDataList()
                )
            }
        }
    }

    fun refreshRecordDetails(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    recordsDetails = recordRepository.getAllDataList().map { it.toRecordDetails() }
                )
            }
        }
    }

    fun upsertRecord(recordDetails: RecordDetails){
        if(recordDetailsValidator(recordDetails)){
            viewModelScope.launch {
                recordRepository.upsertItem(recordDetails.toRecord())
            }
            Log.i("Records", "upsertRecordDetails ${recordDetails.toString()}, validator=${recordDetailsValidator(recordDetails)}")
            Log.i("Records", "upsertRecord ${recordDetails.toRecord().toString()}")
        }
    }

    private fun recordDetailsValidator(recordDetails: RecordDetails): Boolean{
        return true
    }

    private fun Record.toRecordDetails(): RecordDetails = RecordDetails(
        idRecord = this.idRecord,
        idMarker = this.idMarker, // uiState.value.markers.find { idMarker == this.idMarker }?.name ?: "not found",
        idStudent = this.idStudent,
        pairNum = this.pairNum,
        date = this.date
    )

    private fun RecordDetails.toRecord(): Record = Record(
        idRecord = this.idRecord,
        idMarker = this.idMarker,
        idStudent = this.idStudent,
        pairNum = this.pairNum,
        date = this.date
    )

    data class RecordDetails(
        val idRecord: Int = 0,
        var idMarker: Int = 0,
        val idStudent: Int = 0,
        val pairNum: Int = 0,
        val date: String = "01.01.1999"
    )

    data class RecordsScreenUiState(
        var recordsDetails: List<RecordDetails> = listOf<RecordDetails>(),
        var markers: List<Marker> = listOf<Marker>(),
        var markerTypes: List<MarkerType> = listOf<MarkerType>(),
        var students: List<Student> = listOf<Student>()
    )
}