package com.fedor.attendancerecording.view.screens

import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

import com.fedor.attendancerecording.model.entity.Student
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.view.components.ActionList

@Preview
@Composable
public fun Students(){
    val viewModel: StudentsViewModel = StudentsViewModel()

    ActionList<Student>(onEditClick = {}
        , onDeleteClick = {}
        , onAddClick = {}
        , addIconFun = @Composable {}
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