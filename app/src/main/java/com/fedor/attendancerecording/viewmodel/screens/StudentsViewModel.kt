package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.data.entity.Student
import com.fedor.attendancerecording.data.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.viewmodel.ActionListable
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentsViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel(), ActionListable {

    val studentsUiState: StateFlow<StudentsUiState> =
        studentRepository.getAllDataStream().map { it -> StudentsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StudentsUiState()
            )

    fun deleteStudent(student: Any){
        viewModelScope.launch {
            studentRepository.deleteItem(
                student as Student
            )
            Log.i("Students_View_Model", "Studend_Deleted")
        }
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}
public data class StudentsUiState(val studentList: List<Student> = listOf())
