package com.fedor.attendancerecording.view.screens

import com.fedor.attendancerecording.viewmodel.screens.MarkersViewModel
import com.fedor.attendancerecording.model.entity.Marker

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.view.components.ActionList

@Preview
@Composable
fun Markers(){
    val viewModel: MarkersViewModel = MarkersViewModel()

    ActionList<Marker>(onEditClick = {}
        , onDeleteClick = {}
        , onAddClick = {}
        , addIconFun = @Composable {}
        , itemsList = listOf<Marker>(
            Marker(1, "name1", 1)
            , Marker(2, "name2", 1)
            , Marker(3, "name3", 1)
            , Marker(4, "name4", 1)
            , Marker(5, "name5", 1)
            , Marker(6, "name6", 1)
            , Marker(7, "name7", 1)
            , Marker(8, "name8", 1)
            , Marker(9, "name9", 1)
        )
    )
}