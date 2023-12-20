package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "marker_type", indices = [androidx.room.Index(value = ["name"], unique = true)])
data class MarkerType(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_marker_type") val idMarkerType : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "description") val description : String?
)