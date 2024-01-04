package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.EditStudentDestination
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditStudentViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel() {
    private val studentId: Int = checkNotNull(savedStateHandle[EditStudentDestination.navArgumentName])

    val actionNameStringResId = when(studentId == 0){
        true -> R.string.add // we add a new student
        false -> R.string.save_changes // we have a student
    }

    var studentUiState by mutableStateOf(StudentUiState())
        private set

    init{
        viewModelScope.launch {
            studentUiState = studentRepository.getOneItemStreamById(studentId)
                .filterNotNull()
                .first()
                .toStudentUiState(isInputValid = true)
        }
    }

    fun updateUiState(studentDetails: StudentDetails){
        studentUiState = StudentUiState(studentDetails = studentDetails,
                            isInputValid = studentDetailsValidator(studentDetails))
    }

    fun upsertStudent(){
        if(studentDetailsValidator()){
            viewModelScope.launch {
                studentRepository.upsertItem(studentUiState.studentDetails.toStudent())
            }
        }
    }

    private fun studentDetailsValidator(studentDetails: StudentDetails = studentUiState.studentDetails): Boolean{
        return studentDetails.name.isNotEmpty()
                && studentDetails.surname.isNotEmpty()
                && studentDetails.patronymic.isNotEmpty()
    }

    data class StudentUiState(
        val studentDetails: StudentDetails = StudentDetails(),
        val isInputValid: Boolean = false
    )

    data class StudentDetails(
        val id: Int = 0,
        val name: String = "",
        val surname: String = "",
        val patronymic: String = ""
    )

    private fun Student.toStudentDetails() : StudentDetails = StudentDetails(
        idStudent,
        name,
        surname,
        if (patronymic.isNullOrEmpty()) "" else patronymic
    )

    private fun Student.toStudentUiState(isInputValid: Boolean = false): StudentUiState = StudentUiState(
        studentDetails = this.toStudentDetails(),
        isInputValid = isInputValid
    )

    private fun StudentDetails.toStudent(): Student = Student(
        id,
        name,
        surname,
        if ( patronymic.isNullOrEmpty() ) "" else patronymic
    )
}