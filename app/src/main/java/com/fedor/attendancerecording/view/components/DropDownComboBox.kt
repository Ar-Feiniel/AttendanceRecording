package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun <T> DropDownComboBox(
    onSelectedItemChanged: () -> Unit,
    selectedItem: MutableState<String>,
    itemsList: List<ComboBoxItem<T>>,
    modifier: Modifier =
        Modifier
            .width(100.dp)
            .height(40.dp)
            .padding(PaddingValues(0.dp))
) {
    var expanded by remember { mutableStateOf(false) }
    Column() {
        Button(
            shape = RoundedCornerShape(10.dp), modifier = modifier,
            onClick = { expanded = !expanded },
            contentPadding = PaddingValues(2.dp)
        ) {
            // Стандартный текстовый компонент Jetpack Compose
            Text(
                text = "${selectedItem.value}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(2f)
                    .padding(0.dp)
            )
            // Стандартный компонент Jetpack Compose, позволяющий использовать векторные значки в соответсивии с рекомендациями Material Design
            Icon(
                imageVector = Icons.Outlined.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .padding(0.dp)
                    .rotate(
                        when(expanded) {
                            true -> 180f
                            false -> 0f
                        }
                    )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            itemsList.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item.visibleText,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    onClick = {
                        selectedItem.value = item.visibleText
                        expanded = !expanded
                        onSelectedItemChanged()
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors =
                    if (selectedItem.value == item.visibleText)
                        MenuDefaults.itemColors(textColor = MaterialTheme.colorScheme.tertiary)
                    else
                        MenuDefaults.itemColors()
                )
            }
        }
    }
}

data class ComboBoxItem<T>(
    val itemObject: T,
    val visibleText: String = ""
)

@Preview(heightDp = 200, widthDp = 120)
@Composable
private fun DropDownComboBox_Preview(){
    Box(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ){
        DropDownComboBox<String>(
            onSelectedItemChanged = {},
            selectedItem = remember{ mutableStateOf("item1") },
            itemsList = listOf("item1", "item2", "item3")
                .map{ it -> ComboBoxItem<String>(it, it) }
        )
    }
}