package com.fedor.attendancerecording.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_day", indices = [Index(value = ["date"], unique = true)])
data class ScheduleDay (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_day") val idDay: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "is_working_day", defaultValue = true.toString()) val isWorkingDay: Boolean
)