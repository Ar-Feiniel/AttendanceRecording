package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "marker", indices = [Index(value = ["name"], unique = true)])
data class Marker(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_marker") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id_marker_type") val idMarkerType: Int
)