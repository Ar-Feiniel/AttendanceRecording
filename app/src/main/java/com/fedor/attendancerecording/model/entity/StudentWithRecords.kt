package com.fedor.attendancerecording.model.entity
import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithRecords(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "id_student",
        entityColumn = "id_student"
    )
    val records: List<Record>?
)