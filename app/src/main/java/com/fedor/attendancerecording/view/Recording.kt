package com.fedor.attendancerecording.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fedor.attendancerecording.view.components.BurgerMenuButton


@Composable
public fun Recording()
{

    Column {
        BurgerMenuButton()
        Spacer(modifier = Modifier.width(12.dp))
        PairTabRow()
    }
}

@Preview
@Composable
internal fun PairTabRow(){
    var tabIndex by remember { mutableStateOf<Int>(0) }
    val tabs = listOf("1", "2", "3", "4")
    Box(contentAlignment = Alignment.Center) {
        TabRow(selectedTabIndex = tabIndex
                , modifier = Modifier.fillMaxWidth(0.8f)
            ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex != index,
                    onClick = { tabIndex = index },
                    selectedContentColor = Color(102, 102, 255),
                    unselectedContentColor = Color(153, 153, 255)
                )
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