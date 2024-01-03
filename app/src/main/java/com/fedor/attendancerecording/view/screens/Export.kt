package com.fedor.attendancerecording.view.screens

import android.widget.Space
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
import com.fedor.attendancerecording.view.components.ComboBoxItem
import com.fedor.attendancerecording.view.components.DropDownComboBox
import com.fedor.attendancerecording.view.components.PairButtonsRow
import com.fedor.attendancerecording.view.components.export.ExportVariantCard
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.ExportViewModel
import java.time.LocalDate

@Composable
fun Export(
    viewModel: ExportViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {

    var isExportDialogOpen by remember{ mutableStateOf(false) }
    val selectedFormat = remember{ mutableStateOf("") }
    val selectedDate = remember{ mutableStateOf(LocalDate.now().toString()) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ExportVariantCard(
            iconResId = R.drawable.export_excel,
            exportFormat = "xlsx",
            onCardClick = {
                selectedFormat.value = "xlsx"
                isExportDialogOpen = !isExportDialogOpen
            })
        Spacer(modifier = Modifier.height(4.dp))
        ExportVariantCard(
            iconResId = R.drawable.export_csv,
            exportFormat = "csv",
            onCardClick = {
                selectedFormat.value = "csv"
                isExportDialogOpen = !isExportDialogOpen
            })
    }

    if(isExportDialogOpen){
        ExportDialog(
            selectedDate = viewModel.dateLabelText,
            onDeleteConfirm = {
                isExportDialogOpen = !isExportDialogOpen
            },
            onDeleteCancel = {
                isExportDialogOpen = !isExportDialogOpen
            },
            viewModel = viewModel
        )
    }
}

@Composable
fun ExportDialog(
    viewModel: ExportViewModel,
    selectedDate: String = LocalDate.now().toString(),
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit
){
    val selectedFormat: MutableState<String> = remember{ mutableStateOf(viewModel.formats.first()) }

    AlertDialog(
        onDismissRequest = {  },
        title = { Text(text = stringResource(id = R.string.export_settings)) },
        text = {
            Column {
                PairButtonsRow(
                    onLeftButtonClick = viewModel::goToPreviousMonth,
                    leftButtonText = "<",
                    onRightButtonClick = viewModel::goToNextMonth,
                    rightButtonText = ">",
                    buttonHeight = 40.dp,
                    buttonWidth = 40.dp,
                    centralContent = { Text(text = selectedDate) }
                )
                Spacer( modifier = Modifier.height(3.dp) )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text= stringResource(id = R.string.format),
                        modifier = Modifier.weight(1f)
                    )
                    DropDownComboBox(
                        onSelectedItemChanged = { /*TODO*/ },
                        selectedItem = selectedFormat,
                        itemsList = viewModel.formats.map { it -> ComboBoxItem<String>(visibleText = it, itemObject = it) },
                        modifier = Modifier.weight(1f)
                    )
                }
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