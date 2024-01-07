package com.fedor.attendancerecording.view.screens

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.entity.Student
import com.fedor.attendancerecording.view.components.ActionList
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

@Composable
public fun Students(
    onAddStudentClick: () -> Unit,
    onEditStudentClick: (idStudent: Int) -> Unit,
    viewModel: StudentsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val studentsUiState by viewModel.studentsUiState.collectAsState()

    ActionList<Student>(
        actionClass = Student::class
        , onEditClick = onEditStudentClick
        , onDeleteClick = viewModel::deleteStudent
        , onAddClick = onAddStudentClick
        , addIconCompose = @Composable {modifier ->  Icon( ImageVector.vectorResource(R.drawable.student_add), null, modifier = modifier)}
        , itemsList = studentsUiState.studentList
    )
}
