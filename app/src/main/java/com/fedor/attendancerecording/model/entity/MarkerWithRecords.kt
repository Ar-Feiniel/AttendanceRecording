package com.fedor.attendancerecording.model.entity
import androidx.room.Embedded
import androidx.room.Relation

data class MarkerWithRecords(
    @Embedded val marker: Marker,
    @Relation(
        parentColumn = "id_marker",
        entityColumn = "id_marker"
    )
    val records: List<Record>?
)
