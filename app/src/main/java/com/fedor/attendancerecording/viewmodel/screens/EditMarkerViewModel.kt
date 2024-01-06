package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.EditMarkerDestination
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerTypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditMarkerViewModel(
    savedStateHandle: SavedStateHandle,
    private val markerRepository: MarkerRepository,
    private val markerTypeRepository: MarkerTypeRepository
) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    private val _uiState = MutableStateFlow(MarkerScreenUiState())
    var uiState: StateFlow<MarkerScreenUiState> = _uiState.asStateFlow()

    private val markerId: Int = checkNotNull(savedStateHandle[EditMarkerDestination.navArgumentName])

    val actionNameStringResId = when (markerId == 0) {
        true -> R.string.add // we add a new student
        false -> R.string.save_changes // we have a student
    }

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    markerDetails = markerRepository.getItemById(markerId)?.toMarkerDetails() ?: MarkerDetails(),
                    isInputValid = true,
                    markerTypesList = markerTypeRepository.getAllDataList()
                )
            }
        }
    }

    fun updateUiState(currentMarkerDetails: MarkerDetails){
        _uiState.update {
            it.copy(
                markerDetails = currentMarkerDetails
            )
        }
    }

    fun upsertMarker(){
        if(markerDetailsValidator()){
            viewModelScope.launch {
                markerRepository.upsertItem(uiState.value.markerDetails.toMarker())
            }
        }
    }

    private fun markerDetailsValidator(markerDetails: MarkerDetails = uiState.value.markerDetails): Boolean {
        return markerDetails.idMarker >= 0
                && markerDetails.name.isNotEmpty()
                && markerDetails.idMarkerType > 0
    }

    data class MarkerDetails(
        val idMarker: Int = 0,
        val name: String = "",
        val idMarkerType: Int = 0
    )

    data class MarkerScreenUiState(
        val markerDetails: MarkerDetails = MarkerDetails(),
        val isInputValid: Boolean = false,
        val markerTypesList: List<MarkerType> = listOf()
    )

    private fun Marker.toMarkerDetails(): MarkerDetails = MarkerDetails(
        idMarker = idMarker,
        name = name,
        idMarkerType = idMarkerType
    )

    private fun MarkerDetails.toMarker(): Marker = Marker(
        idMarker = idMarker,
        name = name,
        idMarkerType = idMarkerType
    )
}