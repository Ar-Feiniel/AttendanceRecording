package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.RecordsDestination
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerTypeRepository
import com.fedor.attendancerecording.model.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate

public class RecordsViewModel(
    savedStateHandle: SavedStateHandle,
    private val recordRepository: RecordRepository,
    private val studentRepository: StudentRepository,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository
) : ViewModel() {
    private val _dateString: String = checkNotNull(savedStateHandle[RecordsDestination.navArgumentName])

    private val selectedDate: LocalDate
        get() {
            val dateParts: Array<Int> = arrayOf()
            for ((index, item) in _dateString.split(".").iterator().withIndex()){
                dateParts[index] = item.toInt()
            }
            return LocalDate.of(dateParts[2], dateParts[1], dateParts[0])
        }

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    var recordsUiState: StateFlow<RecordsListUiState> =
        recordRepository.getAllDataStream().map { it -> RecordsListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(StudentsViewModel.TIMEOUT_MILLIS),
                initialValue = RecordsListUiState()
            )

    var markersUiState: StateFlow<MarkersUiState> =
        markerRepository.getAllDataStream().map { it -> MarkersUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(StudentsViewModel.TIMEOUT_MILLIS),
                initialValue = MarkersUiState()
            )

    var markersTypeUiState: StateFlow<MarkerTypeUiState> =
        markerTypeRepository.getAllDataStream().map { it -> MarkerTypeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(StudentsViewModel.TIMEOUT_MILLIS),
                initialValue = MarkerTypeUiState()
            )

    var studentUiState: StateFlow<StudentsUiState> =
        studentRepository.getAllDataStream().map { it -> StudentsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(StudentsViewModel.TIMEOUT_MILLIS),
                initialValue = StudentsUiState()
            )

    data class StudentsUiState( val studentsList: List<Student> = listOf() )

    data class MarkersUiState( val markersList: List<Marker> = listOf() )

    data class MarkerTypeUiState(val markerTypesList: List<MarkerType> = listOf())

    data class RecordsListUiState( val recordsDetailsList: List<Record> = listOf() )
}