package com.fedor.attendancerecording.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.fedor.attendancerecording.R

@Composable
public fun DeleteConfirmationDialog(
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
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(id = R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(id = R.string.yes))
            }
        },
    )
}