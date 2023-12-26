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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecordsViewModel(
    savedStateHandle: SavedStateHandle,
    private val recordRepository: RecordRepository,
    private val studentRepository: StudentRepository,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository
) : ViewModel() {
    private val _dateString: String = checkNotNull(savedStateHandle[RecordsDestination.navArgumentName])

    private val _uiState: MutableStateFlow<RecordsScreenUiState> = MutableStateFlow(RecordsScreenUiState())
    val uiState: StateFlow<RecordsScreenUiState> = _uiState.asStateFlow()
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

    // данные извлекаются из репозитория и сопоставляются с состоянием пользовательского интерфейса (RecordsUiState)
    var recordsUiState: StateFlow<RecordsUiState> =
        recordRepository.getAllDataStream().map { it -> RecordsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(StudentsViewModel.TIMEOUT_MILLIS),
                initialValue = RecordsUiState()
            )

    // можно хранить как обычный список (данные будут только просматриваться), переделать (готово)
    init{
        refreshUiState()
    }

    private fun refreshUiState(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    students = studentRepository.getAllDataList(),
                    markers = markerRepository.getAllDataList(),
                    markerTypes = markerTypeRepository.getAllDataList()
                )
            }
        }
    }

    data class RecordsUiState( val recordsDetailsList: List<Record> = listOf() )
    data class RecordsScreenUiState(
        var markers: List<Marker> = listOf<Marker>(),
        var markerTypes: List<MarkerType> = listOf<MarkerType>(),
        var students: List<Student> = listOf<Student>()
    )
}