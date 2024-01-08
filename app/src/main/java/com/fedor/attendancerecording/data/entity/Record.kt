package com.fedor.attendancerecording.data.entity
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "record",
    indices = [Index(value = ["id_student","date", "pair_num"], unique = true)],
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Marker::class,
            parentColumns = ["id_marker"],
            childColumns = ["id_marker"],
            onDelete = CASCADE,
            onUpdate = CASCADE),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id_student"],
            childColumns = ["id_student"],
            onDelete = CASCADE,
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