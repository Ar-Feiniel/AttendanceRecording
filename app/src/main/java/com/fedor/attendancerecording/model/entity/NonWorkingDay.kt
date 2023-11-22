package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date

@Entity
data class NonWorkingDay (
    @PrimaryKey(autoGenerate = true) val idNotWorkingDay: Int,
    @ColumnInfo() val date: Date,
    @ColumnInfo() val dayType: String
)