package com.fedor.attendancerecording.view.components

import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.R

@Composable
public fun <T: Any> ActionList(
                        onEditClick: (id: Int) -> Unit,
                        onDeleteClick: (id: Int) -> Unit,
                        onAddClick: (id: Int) -> Unit,
                        addIconFun: @Composable (onAddClick: () -> Unit) -> Unit,
                        itemsList: List<T>){
    Column(){
        itemsList.forEach{it ->
            Row(){
                when(it::class)
                {
                    Student::class -> {
                        val student = (it as Student)
                        ListItem(text = "${student.name} ${student.surname} ${student.patronymic}",
                            id = student.idStudent,
                            onEditClick = onEditClick,
                            onDeleteClick = onDeleteClick)
                    }
                    Marker::class -> {
                        val marker = (it as Marker);
                        ListItem(text = marker.name,
                            id = marker.idMarker,
                            onEditClick = onEditClick,
                            onDeleteClick = onDeleteClick)
                    }
                    else -> {
                        Text(text = "fuck u")
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(
    text: String,
    id: Int,
    onEditClick: (id: Int) -> Unit,
    onDeleteClick: (id: Int) -> Unit,
){
    Row(){
        Box(modifier = Modifier.fillMaxWidth(0.5f)){
            Text(text = text)
        }
        Row(modifier = Modifier.fillMaxWidth()
            , horizontalArrangement = Arrangement.SpaceEvenly){
            //edit
            IconButton(onClick = { onEditClick(id) }) {
                Icon(ImageVector.vectorResource(R.drawable.delete), contentDescription = null)
            }
            //delete
            IconButton(onClick = { onDeleteClick(id) }) {
                Icon(ImageVector.vectorResource(R.drawable.edit), contentDescription = null)
            }
        }
    }
}