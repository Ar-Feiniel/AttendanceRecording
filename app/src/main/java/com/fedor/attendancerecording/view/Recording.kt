package com.fedor.attendancerecording.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fedor.attendancerecording.ui.theme.AttendanceRecordingTheme
import com.fedor.attendancerecording.view.components.BurgerMenuButton
import com.fedor.attendancerecording.view.components.DateLabel

@Preview
@Composable
public fun Recording()
{
    AttendanceRecordingTheme {
        Column {
            BurgerMenuButton()
            Spacer(modifier = Modifier.height(12.dp))
            DateLabel("26.ноябрь.2023")
            Row( modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Пара")
            }
            Spacer(modifier = Modifier.height(12.dp))
            PairTabRow()
            Spacer(modifier = Modifier.height(12.dp))
            StudentsList()
        }
    }
}
@Composable
internal fun PairTabRow(){
    var tabIndex by remember { mutableStateOf<Int>(0) }
    val tabs = listOf("1", "2", "3", "4", "5")
    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex
            , modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = tabIndex != index,
                    onClick = { tabIndex = index },
                    ){
                        Box(contentAlignment = Alignment.Center
                            , modifier = Modifier
                                .background((Color(102, 102, 255)))
                                .fillMaxWidth()
                        ){
                            Text(text = "$index")
                        }
                }
            }
        }
        when (tabIndex) {
            0 -> Text(text = "0")
            1 -> Text(text = "1")
            2 -> Text(text = "2")
            3 -> Text(text = "3")
            4 -> Text(text = "4")
        }
    }
}

@Composable
internal fun StudentsList(){
    val students: List<String> = listOf("Хрюшев Хрюш Хрюшыч1"
        , "Хрюшев Хрюш Хрюшыч2"
        , "Хрюшев Хрюш Хрюшыч3"
        , "Хрюшев Хрюш Хрюшыч4"
        , "Хрюшев Хрюш Хрюшыч5"
        , "Хрюшев Хрюш Хрюшыч6"
        , "Хрюшев Хрюш Хрюшыч7")
    val markers: List<String> = listOf("2у", "2", "1")
    Column() {
        students.forEach {string ->
            Spacer(modifier = Modifier.height(10.dp))
            Row(){
                Column(modifier = Modifier.fillMaxWidth(0.5f)) {
                    Text(text = string)
                }
                Column(modifier = Modifier.fillMaxWidth()
                            .wrapContentSize(Alignment.Center),
                    horizontalAlignment = Alignment.Start,) {
                    MarkersComboBox(markers = markers)
                }
            }
        }
    }
}

@Composable
internal fun MarkersComboBox(markers: List<String>){
    var expanded by remember { mutableStateOf(false) }
    Button(shape = RoundedCornerShape(10.dp)
        , modifier = Modifier.width(100.dp)
                            .height(30.dp)
        , onClick = { expanded = !expanded}) {
        Text(text = "marker")
    }
    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        markers.forEach{ string ->
            DropdownMenuItem(text = {Text(text = string)},onClick = { /*TODO*/ })
        }
    }
}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun Demo_ExposedDropdownMenuBox() {
//    val context = LocalContext.current
//    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha")
//    var expanded by remember { mutableStateOf(false) }
//    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(32.dp)
//    ) {
//        ExposedDropdownMenuBox(
//            expanded = expanded,
//            onExpandedChange = {
//                expanded = !expanded
//            }
//        ) {
//            TextField(
//                value = selectedText,
//                onValueChange = {},
//                readOnly = true,
//                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                modifier = Modifier.menuAnchor()
//            )
//
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                coffeeDrinks.forEach { item ->
//                    DropdownMenuItem(
//                        text = { Text(text = item) },
//                        onClick = {
//                            selectedText = item
//                            expanded = false
//                            Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
//                        }
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
internal fun drop2(){
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)){
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Load") },
                onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
            )
            DropdownMenuItem(
                text = { Text("Save") },
                onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
            )
        }
    }
}

@Composable
fun Demo_DropDownMenu() {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Load") },
                onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
            )
            DropdownMenuItem(
                text = { Text("Save") },
                onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
            )
        }
    }
}