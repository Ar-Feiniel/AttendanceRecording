package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.viewmodel.ActionListable
import kotlinx.coroutines.launch

public final class StudentsViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel(), ActionListable {
    fun addData(){
        viewModelScope.launch {
            studentRepository.insertItem(
                Student(
                    idStudent = 0,
                    name = "name",
                    surname = "surn",
                    patronymic = null
                )
            )
        }
    }
}