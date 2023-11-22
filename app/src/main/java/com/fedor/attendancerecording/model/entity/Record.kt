package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date

@Entity
data class Record(
    @PrimaryKey(autoGenerate = true) val idRecord: Int,
    @ColumnInfo() val idMarker: Int,
    @ColumnInfo() val idStudent: Int,
    @ColumnInfo() val pairNum: Int,
    @ColumnInfo() val date: Date
)
