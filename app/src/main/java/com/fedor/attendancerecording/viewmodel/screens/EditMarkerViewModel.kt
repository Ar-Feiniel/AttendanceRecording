package com.fedor.attendancerecording.viewmodel.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.EditMarkerDestination
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerTypeRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

public class EditMarkerViewModel(
    savedStateHandle: SavedStateHandle,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository
) : ViewModel(){
    public val markerId: Int = checkNotNull(savedStateHandle[EditMarkerDestination.navArgumentName])

    val actionNameStringResId = when(markerId == 0){
        true -> R.string.add // we add a new student
        false -> R.string.save_changes // we have a student
    }

    var markerUiState: MarkerUiState by mutableStateOf(MarkerUiState())
        private set

    var markersTypeList: List<MarkerType> = listOf<MarkerType>()

    init{
        viewModelScope.launch {
            markerUiState = markerRepository.getOneItemStreamById(markerId)
                .filterNotNull()
                .first()
                .toMarkerUiState(isInputValid = true)
            markersTypeList = markerTypeRepository.getAllDataList()
        }
    }
    fun updateUiState(markerDetails: MarkerDetails){
        markerUiState = EditMarkerViewModel.MarkerUiState(
            markerDetails = markerDetails,
            isInputValid = validateMarkerDetails(markerDetails = markerDetails)
        )
        Log.i("EditMarkerViewModel", "Update_Ui_State")
    }

    private fun validateMarkerDetails(markerDetails: MarkerDetails = markerUiState.markerDetails): Boolean{
        return true
    }
    data class MarkerUiState(
        val markerDetails: MarkerDetails = MarkerDetails(),
        val isInputValid: Boolean = false
    )

    data class MarkerDetails(
        val idMarker: Int = 0,
        val name: String = "",
        val markerType: String = ""
    )

    private fun Marker.toMarkerUiState(isInputValid: Boolean): MarkerUiState = MarkerUiState(
        markerDetails = this.toMarkerDetails(),
        isInputValid = isInputValid
    )

    private fun Marker.toMarkerDetails(): MarkerDetails = MarkerDetails(
        idMarker = idMarker,
        name = name,
        markerType = markerTypeRepository.getOneItemById(idMarkerType).name
    )

    private fun MarkerDetails.toMarker(): Marker = Marker(
        idMarker = idMarker,
        name = name,
        idMarkerType = markerTypeRepository.getOneItemByName(markerType).idMarkerType
    )
}