package com.fedor.attendancerecording.model.entity
import androidx.room.Embedded
import androidx.room.Relation

data class MarkerTypeWithMarkers(
    @Embedded val markerType: MarkerType,
    @Relation(
        parentColumn = "id_marker_type",
        entityColumn = "id_marker_type"
    )
    val markers: List<Marker>?
)
