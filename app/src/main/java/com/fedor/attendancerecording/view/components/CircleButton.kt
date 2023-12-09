package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Composable
fun circleButton(){
    Column(){
        circleButton1(onClick = {}, "te")
    }
}


@Composable
fun circleButton1(onClick: () -> Unit,
                  text: String){
    Column(modifier = Modifier.fillMaxWidth()
        .wrapContentSize(Alignment.Center)
    ){
        Box(
            modifier = Modifier.size(45.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ){
            Text(text = text)
        }
    }
}