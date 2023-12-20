package com.fedor.attendancerecording.view.screens

import android.util.Log
import androidx.compose.material3.Icon
import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

import com.fedor.attendancerecording.model.entity.Student
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.ActionList
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider

@Composable
public fun Students(
    onEditStudentClick: (idStudent: Int) -> Unit,
    viewModel: StudentsViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    Log.i("Students_Screen", "StudentsScreenComposing...")
    val studentsUiState by viewModel.studentsUiState.collectAsState()

    ActionList<Student>(
        actionClass = Student::class
        , onEditClick = onEditStudentClick
        , onDeleteClick = viewModel::deleteStudent
        , onAddClick = {}
        , addIconCompose = @Composable {modifier ->  Icon( ImageVector.vectorResource(R.drawable.student_add), null, modifier = modifier)}
        , itemsList = studentsUiState.studentList
    )
}

internal fun onAddClick(){

}

@Composable
internal fun onAddPopUp(){
    // no popup solution
}

@Composable
internal fun onDeletePopUp(){

}

@Preview
@Composable
fun StudentsPreview(){
    Markers({})
}