package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
public fun BurgerMenuButton() {
    Row() {
        IconButton(colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color(255, 255, 255)
        )
            , modifier = Modifier
                .height(60.dp)
                .width(60.dp)
                .padding(start = 10.dp, top = 10.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf<Color>(
                            Color(102, 102, 255),
                            Color(153, 153, 255)
                        ))
                    , shape = RoundedCornerShape(10.dp)
                )
            , onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Menu, contentDescription = "")
        }
    }
}