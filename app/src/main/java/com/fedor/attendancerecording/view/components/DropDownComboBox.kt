package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> DropDownComboBox(
    selectedItem: MutableState<String>,
    itemsList: List<ComboBoxItem<T>>
) {
    var expanded by remember { mutableStateOf(false) }

    Button(shape = RoundedCornerShape(10.dp), modifier = Modifier
        .width(100.dp)
        .height(30.dp)
        .padding(PaddingValues(0.dp)),
        onClick = { expanded = !expanded }
    ) {
        Text(text = "${selectedItem.value}")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        itemsList.forEach { item ->
            DropdownMenuItem(text = { Text(text = item.visibleText) },
                onClick = {
                    selectedItem.value = item.visibleText
                    expanded = !expanded
                })
        }
    }
}

data class ComboBoxItem<T>(
    val itemObject: T,
    val visibleText: String = ""
)