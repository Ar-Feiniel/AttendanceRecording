package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.model.repositories.interfaces.SettingRepository

class SettingsViewModel(
    private val settingRepository: SettingRepository
): ViewModel() {

}