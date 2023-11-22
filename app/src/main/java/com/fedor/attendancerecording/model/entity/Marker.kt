package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marker")
data class Marker(
    @PrimaryKey(autoGenerate = true) val idMarker: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "marker_type") val markerType: Int
)