package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PairButtonsRow(
    onLeftButtonClick: () -> Unit,
    leftButtonText: String = "L",
    onRightButtonClick: () -> Unit,
    rightButtonText: String = "R",
    buttonHeight: Dp = 50.dp,
    buttonWidth: Dp = 80.dp,
    centralContent: @Composable () -> Unit = {}
) {
    val defaultArrangement: Arrangement.HorizontalOrVertical = Arrangement.Center
    val defaultVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically
    val defaultHorizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally

    Row(
        modifier = Modifier.fillMaxWidth()
        .padding(start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // 0.2
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = defaultHorizontalAlignment,
            verticalArrangement = defaultArrangement
        ) {
            PairButton(
                text = leftButtonText,
                onClickAction = onLeftButtonClick,
                height = buttonHeight,
                width = buttonWidth
            )
        }

        // 0.6
        Column(
            modifier = Modifier
                .weight(3f),
            horizontalAlignment = defaultHorizontalAlignment,
            verticalArrangement = defaultArrangement
        ) {
            centralContent()
        }

        // 0.2
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = defaultHorizontalAlignment,
            verticalArrangement = defaultArrangement
        ) {
            PairButton(
                text = rightButtonText,
                onClickAction = onRightButtonClick,
                height = buttonHeight,
                width = buttonWidth
            )
        }
    }
}

@Composable
private fun PairButton(
    text: String,
    onClickAction: () -> Unit,
    height: Dp,
    width: Dp
) {
    Button(
        shape = RoundedCornerShape(20f), modifier = Modifier
            .width(width)
            .height(height), onClick = onClickAction,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(text = text, fontSize = (width.value * 0.5).sp)
    }
}

@Preview
@Composable
private fun PairButtonPreview(text: String, onClickAction: () -> Unit) {
    PairButtonsRow(onLeftButtonClick = { /*TODO*/ }, onRightButtonClick = { /*TODO*/ })
}