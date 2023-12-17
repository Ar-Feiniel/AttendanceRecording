package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.RecordingDestination
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository

public final class EditStudentViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel(){
    private val studentId: Int = checkNotNull(savedStateHandle[RecordingDestination.navArgumentName])
}