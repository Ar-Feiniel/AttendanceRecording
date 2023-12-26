package com.fedor.attendancerecording.model.entity.relations
import androidx.room.Embedded
import androidx.room.Relation
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Record

data class MarkerWithRecords(
    @Embedded val marker: Marker,
    @Relation(
        parentColumn = "id_marker",
        entityColumn = "id_marker"
    )
    val records: List<Record>?
)
