package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "settings")
data class Setting (
    @PrimaryKey()@ColumnInfo(name = "id_setting") val idSetting: Int,
    @ColumnInfo(name = "value") val value: String
)

