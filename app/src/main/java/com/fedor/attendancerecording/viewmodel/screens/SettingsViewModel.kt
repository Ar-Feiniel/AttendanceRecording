package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.data.entity.Setting
import com.fedor.attendancerecording.data.repositories.interfaces.SettingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingRepository: SettingRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<SettingsScreenUiState> = MutableStateFlow(SettingsScreenUiState())
    val uiState: StateFlow<SettingsScreenUiState> = _uiState.asStateFlow()

    data class SettingsScreenUiState(
        var settingsDetails: List<SettingDetails> = listOf<SettingDetails>(),
    )

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    settingsDetails = settingRepository.getAllDataList().map { it.toSettingDetails() }
                )
            }
        }
    }

    fun updateSetting( settingDetails: SettingDetails ): Boolean {
        if( settingDetails.isValid() ){
            viewModelScope.launch {
                settingRepository.updateItem(settingDetails.toSetting())
            }
            return true
        }
        return false
    }

    data class SettingDetails(
        val idSetting: Int = 0,
        val value: String = ""
    ) {
        fun isValid(): Boolean {
            return true
        }
    }

    private fun Setting.toSettingDetails(): SettingDetails = SettingDetails(
        idSetting = idSetting,
        value = value
    )

    private fun SettingDetails.toSetting(): Setting = Setting(
        idSetting = idSetting,
        value = value
    )
}