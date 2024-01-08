package com.fedor.attendancerecording.viewmodel.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fedor.attendancerecording.data.entity.Marker
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.viewmodel.ActionListable
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MarkersViewModel(
    private val markerRepository: MarkerRepository
) : ViewModel(), ActionListable {
    val markersUiState: StateFlow<MarkersUiState> =
        markerRepository.getAllDataStream().map {it -> MarkersUiState(it)}
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = MarkersUiState()
            )

    fun deleteMarker(marker: Any){
        viewModelScope.launch {
            markerRepository.deleteItem(
                marker as Marker
            )
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

public data class MarkersUiState(val markerList: List<Marker> = listOf())