package com.fedor.attendancerecording.view.components.entityeditor

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T: Any> EntityEditor(
    editorData: EntityEditorData<T>,
    additionContentAction: @Composable () -> Unit = {}
){
    Column() {
        Text(text = editorData.action)
        editorData.states.forEach { item ->
            TextField(value = item.state.value, onValueChange = {item.state.value = it})
        }
    }

    additionContentAction()

    Button(onClick = { /*TODO*/ }) {
        
    }

}
data class EntityEditorData<T>(
    val action: String,
    val onSaveClick: (item: T) -> Unit,
    val onBackClick: () -> Unit,
    var states: MutableList<EntityEditorItem<T>>
)
data class EntityEditorItem<T>(
    val fieldName: String = "",
    var state: MutableState<String>
)