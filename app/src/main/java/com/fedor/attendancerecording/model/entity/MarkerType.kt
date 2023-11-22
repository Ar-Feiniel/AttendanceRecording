package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarkerType(
    @PrimaryKey(autoGenerate = true) val iMarkerType : Int,
    @ColumnInfo() val name : String,
    @ColumnInfo() val description : String
)
