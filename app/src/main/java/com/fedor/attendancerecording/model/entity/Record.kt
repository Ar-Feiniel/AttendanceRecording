package com.fedor.attendancerecording.model.entity
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.sql.Date

@Entity(tableName = "record", indices = [Index(value = ["id_student","date", "pair_num"], unique = true)])
data class Record(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_record") val idRecord: Int,
    @ColumnInfo(name = "id_marker") val idMarker: Int,
    @ColumnInfo(name = "id_student") val idStudent: Int,
    @ColumnInfo(name = "pair_num") val pairNum: Int,
    @ColumnInfo(name = "date") val date: String
)