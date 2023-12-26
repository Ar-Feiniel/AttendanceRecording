package com.fedor.attendancerecording.model.entity.relations
import androidx.room.Embedded
import androidx.room.Relation
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.entity.Student

data class StudentWithRecords(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "id_student",
        entityColumn = "id_student"
    )
    val records: List<Record>?
)