package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.annotation.BoolRes
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.viewmodel.ActionListable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

public final class StudentsViewModel(
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
    fun addStudent(student: Any){
        viewModelScope.launch {
            studentRepository.insertItem(
                student as Student
            )
            Log.i("Students_View_Model", "Studend_Added")
        }
    }

    fun deleteStudent(student: Any){
        viewModelScope.launch {
            studentRepository.deleteItem(
                student as Student
            )
            Log.i("Students_View_Model", "Studend_Deleted")
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    public data class StudentsUiState(val studentList: List<Student> = listOf())

    public data class StudentsParamsUiState(val studentParamsList: List<StudentParams> = listOf())

    public fun Student.toStudentParams(): StudentParams = StudentParams( idStudent, name, surname, patronymic )

    public fun StudentParams.toStudent(): Student = Student( id, name, surname, patronymic )
}

public data class StudentParams(val id: Int = 0,
                                val name: String = "",
                                val surname: String = "",
                                val patronymic: String? = "")

//public final class StudentEditViewModel(
//    savedStateHandle: SavedStateHandle,
//    private val studentRepository: StudentRepository
//): ViewModel() {
//    // ItemEditViewModel
//    var studentUiState by mutableStateOf(StudentParams())
//        private set
//
//    //private val studentId: Int = checkNotNull()
//}