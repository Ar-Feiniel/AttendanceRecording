package com.fedor.attendancerecording.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_day", indices = [Index(value = ["date"], unique = true)])
data class ScheduleDay (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_schedule_day") val idDay: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "pairs", defaultValue = "3") val pairs: Int,
    @ColumnInfo(name = "is_working_day", defaultValue = true.toString()) val isWorkingDay: Boolean
)