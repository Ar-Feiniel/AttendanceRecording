package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class MarkerTypeWithMarkers(
    @Embedded val markerType: MarkerType,
    @Relation(
        parentColumn = "iMarkerType",
        entityColumn = "idMarker"
    )
    val markers: List<Marker>
)
