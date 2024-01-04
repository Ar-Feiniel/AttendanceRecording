package com.fedor.attendancerecording.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.PairButtonsRow
import com.fedor.attendancerecording.view.components.export.ExportVariantCard
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.ExportViewModel
import java.io.File

@Composable
fun Export(
    viewModel: ExportViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    viewModel.updateRecordsCount()
    var isExportDialogOpen by remember{ mutableStateOf(false) }
    val selectedFormat = remember{ mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        viewModel.exportFormats.forEach{
            ExportVariantCard(
                iconResId = it.value,
                exportFormat = it.key,
                onCardClick = {
                    selectedFormat.value = it.key
                    isExportDialogOpen = !isExportDialogOpen
                })
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

    if(isExportDialogOpen){
        ExportDialog(
            viewModel = viewModel,
            selectedFormat = selectedFormat,
            onConfirmClick = {
                Log.i("create file", "${File("test.txt").createNewFile()}")
                isExportDialogOpen = !isExportDialogOpen
            },
            onCancelClick = {
                isExportDialogOpen = !isExportDialogOpen
            }
        )
    }
}

@Composable
fun ExportDialog(
    viewModel: ExportViewModel,
    selectedFormat: MutableState<String>,
    onConfirmClick: () -> Unit,
    onCancelClick: () -> Unit
){
    AlertDialog(
        onDismissRequest = {  },
        title = {
            Row(){
                Text(text = stringResource(id = R.string.export_to))
                Text(text = selectedFormat.value)
            }
        },
        text = {
            Column {
                Row(){
                    Text(text = "Найдено записей: ")
                    Text(text = viewModel.recordsCount.toString())
                }
                PairButtonsRow(
                    onLeftButtonClick = viewModel::goToPreviousMonth,
                    leftButtonText = "<",
                    onRightButtonClick = viewModel::goToNextMonth,
                    rightButtonText = ">",
                    buttonHeight = 40.dp,
                    buttonWidth = 40.dp,
                    centralContent = { Text(text = viewModel.dateLabelText) }
                )
            }
        },
        confirmButton = {
            IconButton(onClick = onConfirmClick) {
                Icon(ImageVector.vectorResource(id = R.drawable.check,), contentDescription = "enter")
            }
        },
        dismissButton = {
            IconButton(onClick = onCancelClick) {
                Icon(ImageVector.vectorResource(id = R.drawable.close,), contentDescription = "close")
            }
        },
    )
}