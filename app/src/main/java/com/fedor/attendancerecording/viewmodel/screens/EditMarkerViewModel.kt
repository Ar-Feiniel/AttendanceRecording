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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class EditMarkerViewModel(
    savedStateHandle: SavedStateHandle,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    private val markerId: Int = checkNotNull(savedStateHandle[EditMarkerDestination.navArgumentName])

    val actionNameStringResId = when (markerId == 0) {
        true -> R.string.add // we add a new student
        false -> R.string.save_changes // we have a student
    }

    var markerUiState: MarkerUiState by mutableStateOf(MarkerUiState())
        private set

    init {
        viewModelScope.launch {
            markerUiState = markerRepository.getOneItemStreamById(markerId)
                .filterNotNull()
                .first()
                .toMarkerUiState(isInputValid = true)
        }
    }

    var markersTypeUiState: StateFlow<MarkerTypeUiState> =
        markerTypeRepository.getAllDataStream().map { it -> MarkerTypeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(StudentsViewModel.TIMEOUT_MILLIS),
                initialValue = MarkerTypeUiState()
            )

    fun updateUiState(markerDetails: MarkerDetails) {
        markerUiState = MarkerUiState(
            markerDetails = markerDetails,
            isInputValid = markerDetailsValidator(markerDetails = markerDetails)
        )
        Log.i("EditMarkerViewModel", "Update_Ui_State")
    }

    fun upsertMarker(){
        if(markerDetailsValidator()){
            viewModelScope.launch {
                markerRepository.upsertItem(markerUiState.markerDetails.toMarker())
            }
        }
        else{
            Log.i("EditMarkerViewModel", "not upserted, validation failed: ${markerUiState.markerDetails.toMarker().toString()}")
        }
    }

    private fun markerDetailsValidator(markerDetails: MarkerDetails = markerUiState.markerDetails): Boolean {
        return markerDetails.idMarker >= 0
                && markerDetails.name.isNotEmpty()
                && markerDetails.markerTypeName.isNotEmpty()
    }

    data class MarkerDetails(
        val idMarker: Int = 0,
        val name: String = "",
        val markerTypeName: String = "on create clean"
    )

    data class MarkerUiState(
        val markerDetails: MarkerDetails = MarkerDetails(),
        val isInputValid: Boolean = false
    )

    data class MarkerTypeUiState(val markerTypesList: List<MarkerType> = listOf())

    private fun Marker.toMarkerUiState(isInputValid: Boolean): MarkerUiState = MarkerUiState(
        markerDetails = this.toMarkerDetails(),
        isInputValid = isInputValid
    )

    private fun Marker.toMarkerDetails(): MarkerDetails = MarkerDetails(
        idMarker = idMarker,
        name = name,
        markerTypeName =
        markersTypeUiState.value.markerTypesList.find { it.idMarkerType == idMarkerType }?.name
            ?: "not found"  // marker type string name here
    )

    private fun MarkerDetails.toMarker(): Marker = Marker(
        idMarker = idMarker,
        name = name,
        idMarkerType = markersTypeUiState.value.markerTypesList.find { it.name == markerTypeName }?.idMarkerType
            ?: -1
    )
}