package com.fedor.attendancerecording.view.components

import com.fedor.attendancerecording.model.entity.Marker
//import com.fedor.attendancerecording.model.entity.Student

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.R
import kotlin.reflect.KClass

@Preview
@Composable
public fun listtest() {
    ActionList<Student>(onEditClick = {}
        , onDeleteClick = {}
        , onAddClick = {}
        , addIconFun = @Composable {}
        , itemsList = listOf<Student>(
            Student(1, "name1")
            , Student(2, "name2")
            , Student(3, "name3")
            , Student(4, "name4")
            , Student(5, "name5")
            , Student(6, "name6")
            , Student(7, "name7")
            , Student(8, "name8")
            , Student(9, "name9")
        )
    )
}
data class Student(
    val id: Int,
    val name: String
)

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
                        val item = (it as Student);
                        Box(modifier = Modifier.fillMaxWidth(0.5f)){
                            Text(text="${item.name}")
                        }
                        Row(modifier = Modifier.fillMaxWidth()
                            , horizontalArrangement = Arrangement.SpaceEvenly){
                            //edit
                            IconButton(onClick = { onEditClick(item.id) }) {
                                Icon(ImageVector.vectorResource(R.drawable.delete), contentDescription = null)
                            }
                            //delete
                            IconButton(onClick = { onDeleteClick(item.id) }) {
                                Icon(ImageVector.vectorResource(R.drawable.edit), contentDescription = null)
                            }
                        }
                    }
                    Marker::class -> {
                        val marker = (it as Marker);
                        Box(modifier = Modifier.fillMaxWidth(0.5f)){
                            Text(text="${marker.name}")
                        }
                        Row(modifier = Modifier.fillMaxWidth()
                            , horizontalArrangement = Arrangement.SpaceEvenly){
                            //edit
                            IconButton(onClick = { onEditClick(marker.id) }) {
                                Icon(ImageVector.vectorResource(R.drawable.delete), contentDescription = null)
                            }
                            //delete
                            IconButton(onClick = { onDeleteClick(marker.id) }) {
                                Icon(ImageVector.vectorResource(R.drawable.edit), contentDescription = null)
                            }
                        }
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
fun ListItem(){

}

