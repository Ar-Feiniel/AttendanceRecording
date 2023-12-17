package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.EditStudentDestination
import com.fedor.attendancerecording.RecordingDestination
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository

public final class EditStudentViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: StudentRepository
) : ViewModel(){
    public val studentId: Int = checkNotNull(savedStateHandle[EditStudentDestination.navArgumentName])
}