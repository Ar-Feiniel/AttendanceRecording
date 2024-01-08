package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.fedor.attendancerecording.R

@Composable
fun DeleteConfirmationDialog(
    deleteObjectStringDescription: String = "",
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit
){
    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(id = R.string.attention)) },
        text = {
               Column {
                   Text(text = stringResource(id = R.string.deleteConfirmation))
                   Text(text = deleteObjectStringDescription)
               }
        },
        confirmButton = {
            IconButton(onClick = onDeleteConfirm) {
                Icon(ImageVector.vectorResource(id = R.drawable.check,), contentDescription = "enter")
            }
        },
        dismissButton = {
            IconButton(onClick = onDeleteCancel) {
                Icon(ImageVector.vectorResource(id = R.drawable.close,), contentDescription = "close")
            }
        },
    )
}

@Preview
@Composable
private fun Preview() {
    DeleteConfirmationDialog(
        deleteObjectStringDescription = "",
        onDeleteConfirm = {},
        onDeleteCancel = {}
    )
}