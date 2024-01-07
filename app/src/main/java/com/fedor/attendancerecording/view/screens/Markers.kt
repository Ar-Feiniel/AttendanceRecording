package com.fedor.attendancerecording.view.screens

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.data.entity.Marker
import com.fedor.attendancerecording.view.components.ActionList
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider
import com.fedor.attendancerecording.viewmodel.screens.MarkersViewModel

@Composable
fun Markers(
    onAddMarkerClick: () -> Unit,
    onEditMarkerClick: (idMarker: Int) -> Unit,
    viewModel: MarkersViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val markerUiState by viewModel.markersUiState.collectAsState()

    ActionList<Marker>(
        actionClass = Marker::class
        , onEditClick = onEditMarkerClick
        , onDeleteClick = viewModel::deleteMarker
        , onAddClick = onAddMarkerClick
        , addIconCompose = @Composable {modifier ->  Icon( ImageVector.vectorResource(R.drawable.marker_add), null, modifier = modifier)}
        , itemsList = markerUiState.markerList
    )
}
