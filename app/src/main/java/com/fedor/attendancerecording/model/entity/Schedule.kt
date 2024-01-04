package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "schedule", indices = [Index(value = ["date"], unique = true)])
data class Schedule (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_day") val idDay: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "is_working_day", defaultValue = true.toString()) val isWorkingDay: Boolean
)