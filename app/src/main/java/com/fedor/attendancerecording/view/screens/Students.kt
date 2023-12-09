package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

import com.fedor.attendancerecording.model.entity.Student
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.ActionList

@PublishedApi
internal const val MaterialIconDimension = 128f
@Preview
@Composable
public fun Students(){
    val viewModel: StudentsViewModel = StudentsViewModel()

    ActionList<Student>(onEditClick = {}
        , onDeleteClick = {}
        , onAddClick = {}
        , addIconCompose = @Composable {modifier ->  Icon( ImageVector.vectorResource(R.drawable.student_add), null, modifier = modifier)}
        , itemsList = listOf<Student>(
            Student(1, "name1", "surname1", "patronumic1")
            , Student(2, "name2", "surname1", "patronumic1")
            , Student(3, "name3", "surname1", "patronumic1")
            , Student(4, "name4", "surname1", "patronumic1")
            , Student(5, "name5", "surname1", "patronumic1")
            , Student(6, "name6", "surname1", "patronumic1")
            , Student(7, "name7", "surname1", "patronumic1")
            , Student(8, "name8", "surname1", "patronumic1")
            , Student(9, "name9", "surname1", "patronumic1")
        )
    )
}