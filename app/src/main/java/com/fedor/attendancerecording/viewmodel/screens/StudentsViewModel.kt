package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.viewmodel.ActionListable

public final class StudentsViewModel(studentRepository: StudentRepository) : ViewModel(), ActionListable {
}