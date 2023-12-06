package com.fedor.attendancerecording.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
public fun listtest(){

}

@Composable
public fun ListEditorScaffold(screenName: String,
                              onEditClick: (id: Int) -> Unit,
                              onDeleteClick: (id: Int) -> Unit,
                              onAddClick: (id: Int) -> Unit,
                              addIconFun: @Composable (onAddClick: () -> Unit) -> Unit){

}


data class Student(
    val id: Int,
    val name: String
)