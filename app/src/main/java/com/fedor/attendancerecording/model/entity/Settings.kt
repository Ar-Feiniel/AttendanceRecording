package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Settings")
data class Settings (
    @PrimaryKey()@ColumnInfo(name = "setting") val setting: String,
    @ColumnInfo(name = "value") val value: String
)

