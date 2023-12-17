package com.fedor.attendancerecording.view.screens

import androidx.compose.material3.Icon
import com.fedor.attendancerecording.viewmodel.screens.MarkersViewModel
import com.fedor.attendancerecording.model.entity.Marker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedor.attendancerecording.R
import com.fedor.attendancerecording.view.components.ActionList
import com.fedor.attendancerecording.viewmodel.AppViewModelProvider

@Composable
fun Markers(
    onEditMarkerClick: (idMarker: Int) -> Unit,
    viewModel: MarkersViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val markerUiState by viewModel.markersUiState.collectAsState()

    ActionList<Marker>(
        actionClass = Marker::class
        , onEditClick = onEditMarkerClick
        , onDeleteClick = viewModel::deleteMarker
        , onAddClick = {}
        , addIconCompose = @Composable {modifier ->  Icon( ImageVector.vectorResource(R.drawable.marker_add), null, modifier = modifier)}
        , itemsList = markerUiState.markerList
    )
}


@Preview
@Composable
fun MarkersPreview(){
    Markers({})
}