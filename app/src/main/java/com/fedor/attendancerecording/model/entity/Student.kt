package com.fedor.attendancerecording.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val idStudent: Int,
    @ColumnInfo() val name: String,
    @ColumnInfo() val surname: String,
    @ColumnInfo() val patronymic: String?
)
