package com.fedor.attendancerecording.view.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.entity.Marker
import com.fedor.attendancerecording.data.entity.Student
import kotlin.reflect.KClass

@Composable
fun <T: Any> ActionList(
    actionClass: KClass<T>,
    onEditClick: (id: Int) -> Unit,
    onDeleteClick: (item: Any) -> Unit,
    onAddClick: () -> Unit,
    addIconCompose: @Composable (modifier: Modifier) -> Unit,
    itemsList: List<T>) {
    Column(
        Modifier.padding(start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onAddClick) {
                addIconCompose(modifier = Modifier.size(128.dp))
            }
        }

        when (actionClass) {
            Student::class -> {
                itemsList.forEach { it ->
                    Row() {
                        val student = (it as Student)
                        ListItem(text = "${student.name} ${student.surname} ${student.patronymic}",
                            id = student.idStudent,
                            onEditClick = onEditClick,
                            onDeleteClick = { onDeleteClick(it) })
                    }
                }
            }

            Marker::class -> {
                itemsList.forEach { it ->
                    Row() {
                        val marker = (it as Marker);
                        ListItem(text = marker.name,
                            id = marker.idMarker,
                            onEditClick = onEditClick,
                            onDeleteClick = { onDeleteClick(it) })
                    }
                }
            }

            else -> {
                Text(text = "what?")
            }
        }
    }
}

@Composable
fun ListItem(
    text: String,
    id: Int,
    onEditClick: (id: Int) -> Unit,
    onDeleteClick: () -> Unit,
) {
    var isDialogOpen: MutableState<Boolean> = remember { mutableStateOf(false) }
    val revertDialogState: () -> Unit = { isDialogOpen.value = !isDialogOpen.value }
    Log.i("Action_Listable_Item", "ActionListableItemCreate")
    val iconModifier: Modifier = Modifier.size(64.dp)
    Row() {
        Box(modifier = Modifier.fillMaxWidth(0.5f)) {
            Text(text = text, overflow = TextOverflow.Clip)
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //edit
            IconButton(onClick = { onEditClick(id) }) {
                Icon(
                    ImageVector.vectorResource(R.drawable.edit),
                    contentDescription = null,
                    modifier = iconModifier
                )
            }

            //delete
            IconButton(onClick = revertDialogState) {
                Icon(
                    ImageVector.vectorResource(R.drawable.delete),
                    contentDescription = null,
                    modifier = iconModifier
                )
            }

            if (isDialogOpen.value) {
                DeleteConfirmationDialog(
                    deleteObjectStringDescription = text,
                    onDeleteConfirm = { onDeleteClick(); revertDialogState() },
                    onDeleteCancel = revertDialogState
                )
            }

        }
    }
}