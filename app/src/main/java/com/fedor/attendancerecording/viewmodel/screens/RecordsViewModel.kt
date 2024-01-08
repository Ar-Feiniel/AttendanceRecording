package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.RecordsDestination
import com.fedor.attendancerecording.data.entity.Marker
import com.fedor.attendancerecording.data.entity.MarkerType
import com.fedor.attendancerecording.data.entity.Record
import com.fedor.attendancerecording.data.entity.ScheduleDay
import com.fedor.attendancerecording.data.entity.Student
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerTypeRepository
import com.fedor.attendancerecording.data.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import com.fedor.attendancerecording.data.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecordsViewModel(
    savedStateHandle: SavedStateHandle,
    private val recordRepository: RecordRepository,
    private val studentRepository: StudentRepository,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository,
    private val scheduleRepository: ScheduleRepository
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
                    markerTypes = markerTypeRepository.getAllDataList(),
                    daySchedule = scheduleRepository.getItemByDate(dateString)?.toScheduleDayDetails() ?: ScheduleDayDetails(date = dateString)
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
    fun refreshScheduleDay(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    daySchedule = scheduleRepository.getItemByDate(dateString)?.toScheduleDayDetails() ?: ScheduleDayDetails(date = dateString)
                )
            }
        }
    }

    fun upsertRecord(recordDetails: RecordDetails){
        if(recordDetailsValidator(recordDetails)){
            viewModelScope.launch {
                recordRepository.upsertItem(recordDetails.toRecord())
            }
        }
    }

    private fun recordDetailsValidator(recordDetails: RecordDetails): Boolean{
        return true
    }

    fun upsertScheduleDay(scheduleDayDetails: ScheduleDayDetails){
        viewModelScope.launch {
            scheduleRepository.upsertItem(scheduleDayDetails.toScheduleDay())
        }
    }

    private fun Record.toRecordDetails(): RecordDetails = RecordDetails(
        idRecord = this.idRecord,
        idMarker = this.idMarker,
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

    private fun ScheduleDay.toScheduleDayDetails() = ScheduleDayDetails(
        idScheduleDay = this.idDay,
        date = date,
        pairs = pairs,
        isWorking = isWorkingDay
    )
    private fun ScheduleDayDetails.toScheduleDay(): ScheduleDay = ScheduleDay(
        idDay = idScheduleDay,
        date = date,
        pairs = pairs,
        isWorkingDay = isWorking
    )
    data class ScheduleDayDetails(
        var idScheduleDay: Int = 0,
        var date: String = "",
        var pairs: Int = 3,
        var isWorking: Boolean = true
    )
    data class RecordsScreenUiState(
        var recordsDetails: List<RecordDetails> = listOf<RecordDetails>(),
        var daySchedule: ScheduleDayDetails = ScheduleDayDetails(),
        var markers: List<Marker> = listOf<Marker>(),
        var markerTypes: List<MarkerType> = listOf<MarkerType>(),
        var students: List<Student> = listOf<Student>()
    )
}