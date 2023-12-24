package com.fedor.attendancerecording.view.components.entityeditor

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Text_EditorComponent(
    value: String = "",
    onValueChange: (String) -> Unit,
    labelStringResId: Int? = null
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            if (labelStringResId != null) {
                Text(text = stringResource(id = labelStringResId))
            } else {}
        },
        modifier = Modifier.fillMaxWidth(),
        enabled = true,
        singleLine = true
    )
}