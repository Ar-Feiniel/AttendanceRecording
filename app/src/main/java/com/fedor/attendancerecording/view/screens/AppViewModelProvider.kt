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

package com.fedor.attendancerecording.view.screens

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fedor.attendancerecording.AttendanceRecordingApp
import com.fedor.attendancerecording.viewmodel.screens.StudentsViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire AttendanceRecordingApp
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for StudentsViewModel
        initializer {
            StudentsViewModel(
                this.createSavedStateHandle(),
                attendanceRecordingApplication().container.studentRepository
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [AttendanceRecordingApp].
 */
fun CreationExtras.attendanceRecordingApplication(): AttendanceRecordingApp =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as AttendanceRecordingApp)
