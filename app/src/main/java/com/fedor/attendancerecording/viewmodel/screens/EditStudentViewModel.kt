package com.fedor.attendancerecording.viewmodel.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.EditStudentDestination
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

public final class EditStudentViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel(){
     val studentId: Int = checkNotNull(savedStateHandle[EditStudentDestination.navArgumentName])
    var studentUiState by mutableStateOf(StudentUiState())
        private set

    init{
        viewModelScope.launch {
            studentUiState = studentRepository.getOneItemStream(studentId)
                .filterNotNull()
                .first()
                .toStudentUiState(isInputValid = true)
        }
    }

    fun updateUiState(studentDetails: StudentDetails){
        studentUiState.copy(studentDetails = studentDetails,
                            isInputValid = validateStudentDetails(studentDetails)
        )
    }

    fun saveStudent(){
        if(validateStudentDetails()){
            viewModelScope.launch {
                studentRepository.insertItem(studentUiState.studentDetails.toStudent())
            }
        }
    }

    private fun validateStudentDetails(studentDetails: StudentDetails = studentUiState.studentDetails): Boolean{
        return true
    }
}

data class StudentUiState(
    val studentDetails: StudentDetails = StudentDetails(),
    val isInputValid: Boolean = false
)

data class StudentDetails(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val patronymic: String? = ""
)

fun Student.toStudentDetails() : StudentDetails = StudentDetails(
    idStudent,
    name,
    surname,
    patronymic
)
fun Student.toStudentUiState(isInputValid: Boolean = false): StudentUiState = StudentUiState(
    studentDetails = this.toStudentDetails(),
    isInputValid = isInputValid
)
fun StudentDetails.toStudent(): Student = Student(
    id,
    name,
    surname,
    patronymic
)

