package com.fedor.attendancerecording.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.RESTRICT
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "marker",
    indices = [Index(value = ["name"], unique = true)],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = MarkerType::class,
            parentColumns = ["id_marker_type"],
            childColumns = ["id_marker_type"],
            onDelete = RESTRICT,
            onUpdate = CASCADE
        )
    )
)
data class Marker(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_marker") val idMarker: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "id_marker_type", defaultValue = "1") val idMarkerType: Int
)