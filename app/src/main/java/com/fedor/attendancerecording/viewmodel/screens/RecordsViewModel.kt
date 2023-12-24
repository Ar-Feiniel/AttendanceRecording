package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.RecordsDestination
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerTypeRepository
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import java.time.LocalDate

public class RecordsViewModel(
    savedStateHandle: SavedStateHandle,
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
}