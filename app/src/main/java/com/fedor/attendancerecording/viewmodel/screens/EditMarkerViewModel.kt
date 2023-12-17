package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fedor.attendancerecording.EditMarkerDestination
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository

public class EditMarkerViewModel(
    savedStateHandle: SavedStateHandle,
    private val studentRepository: MarkerRepository
) : ViewModel(){
    public val markerId: Int = checkNotNull(savedStateHandle[EditMarkerDestination.navArgumentName])
}