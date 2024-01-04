package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.ForeignKey.Companion.RESTRICT
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date

@Entity(
    tableName = "record",
    indices = [Index(value = ["id_student","date", "pair_num"], unique = true)],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Marker::class,
            parentColumns = ["id_marker"],
            childColumns = ["id_marker"],
            onDelete = RESTRICT,
            onUpdate = CASCADE),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id_student"],
            childColumns = ["id_student"],
            onDelete = RESTRICT,
            onUpdate = CASCADE)
    )
)
data class Record(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_record") val idRecord: Int,
    @ColumnInfo(name = "id_marker") val idMarker: Int,
    @ColumnInfo(name = "id_student") val idStudent: Int,
    @ColumnInfo(name = "pair_num") val pairNum: Int,
    @ColumnInfo(name = "date") val date: String
)