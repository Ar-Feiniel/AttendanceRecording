/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fedor.attendancerecording.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fedor.attendancerecording.AttendanceRecordingApplication
import com.fedor.attendancerecording.viewmodel.screens.EditMarkerViewModel
import com.fedor.attendancerecording.viewmodel.screens.EditStudentViewModel
import com.fedor.attendancerecording.viewmodel.screens.ExportViewModel
import com.fedor.attendancerecording.viewmodel.screens.MainCalendarViewModel
import com.fedor.attendancerecording.viewmodel.screens.MarkersViewModel
import com.fedor.attendancerecording.viewmodel.screens.RecordsViewModel
import com.fedor.attendancerecording.viewmodel.screens.ScheduleCalendarViewModel
import com.fedor.attendancerecording.viewmodel.screens.SettingsViewModel
import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire AttendanceRecordingApp
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // main
        initializer {
            MainCalendarViewModel(
                attendanceRecordingApplication().container.scheduleRepository
            )
        }

        // students
        initializer {
            StudentsViewModel(
                attendanceRecordingApplication().container.studentRepository
            )
        }
        // EditStudent
        initializer {
            EditStudentViewModel(
                this.createSavedStateHandle(),
                attendanceRecordingApplication().container.studentRepository
            )
        }

        // markers
        initializer {
            MarkersViewModel(
                attendanceRecordingApplication().container.markerRepository
            )
        }

        // EditMarker
        initializer {
            EditMarkerViewModel(
                this.createSavedStateHandle(),
                attendanceRecordingApplication().container.markerRepository,
                attendanceRecordingApplication().container.markerTypeRepository
            )
        }

        // Records
        initializer {
            RecordsViewModel(
                this.createSavedStateHandle(),
                attendanceRecordingApplication().container.recordRepository,
                attendanceRecordingApplication().container.studentRepository,
                attendanceRecordingApplication().container.markerRepository,
                attendanceRecordingApplication().container.markerTypeRepository,
                attendanceRecordingApplication().container.scheduleRepository
            )
        }

        //Export
        initializer {
            ExportViewModel(
                attendanceRecordingApplication().container.recordRepository
            )
        }

        //Settings
        initializer {
            SettingsViewModel(
                attendanceRecordingApplication().container.settingRepository
            )
        }

        // schedule
        initializer {
            ScheduleCalendarViewModel(
                attendanceRecordingApplication().container.scheduleRepository
            )
        }
    }
}
/**
 * Extension function to queries for [Application] object and returns an instance of
 * [AttendanceRecordingApplication].
 */
fun CreationExtras.attendanceRecordingApplication(): AttendanceRecordingApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AttendanceRecordingApplication)
