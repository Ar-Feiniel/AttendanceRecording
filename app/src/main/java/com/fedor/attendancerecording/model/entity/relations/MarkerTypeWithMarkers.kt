package com.fedor.attendancerecording.model.entity.relations
import androidx.room.Embedded
import androidx.room.Relation
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType

data class MarkerTypeWithMarkers(
    @Embedded val markerType: MarkerType,
    @Relation(
        parentColumn = "id_marker_type",
        entityColumn = "id_marker_type"
    )
    val markers: List<Marker>?
)
