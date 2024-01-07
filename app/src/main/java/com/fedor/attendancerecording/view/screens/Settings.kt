package com.fedor.attendancerecording.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.ApplicationDestination
import com.fedor.attendancerecording.ApplicationDestinationWithNav
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(
    navScreens : List<ApplicationDestinationWithNav>,
    viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize()
    ) {
        uiState.settingsDetails.forEachIndexed { index, it ->
            var isOpen: Boolean by remember { mutableStateOf(false) }
            var isError: Boolean by remember { mutableStateOf(false) }
            var settingValueDetail: String by remember { mutableStateOf(it.value) }

            OutlinedCard(
                onClick = {
                    if (isOpen && !isError) {
                        viewModel.updateSetting(uiState.settingsDetails[index].copy(value = settingValueDetail))
                    } else if (isOpen && isError) {
                        settingValueDetail = it.value
                    }
                    isOpen = !isOpen
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(5.dp)) {
                    Text(text = stringResource(id = it.idSetting))
                    Text(
                        text = settingValueDetail,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    if (isOpen) {
                        TextField(
                            value = settingValueDetail,
                            onValueChange = {
                                isError = !uiState.settingsDetails[index].copy(value = it).isValid()
                                settingValueDetail = it
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(4.dp),
                            isError = isError,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
        navScreens.forEach{ it ->
            OutlinedCard(
                onClick = it.navigateToDestination,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(5.dp)){
                    Icon(
                        imageVector = ImageVector.vectorResource(id = it.appDestination.iconResId!!),
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 0.dp)
                    )
                    Text(
                        text = stringResource(id = it.appDestination.screenNameResId),
                        modifier = Modifier.weight(4f),
                        textAlign = TextAlign.Start
                    )
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}