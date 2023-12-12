package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date

@Entity(tableName = "non_working_day", indices = [Index(value = ["date"], unique = true)])
data class NonWorkingDay (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_nw_day") val idNotWorkingDay: Int,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "day_type") val dayType: String
)