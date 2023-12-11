package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.viewmodel.ActionListable

public final class StudentsViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel(), ActionListable {

}