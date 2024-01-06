package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.ABORT
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao: DataAccessObjectable<Record> {

    @Query("select * from record order by id_record ASC")
    fun getAllStream(): Flow<List<Record>>
    @Query("select * from record order by id_record ASC")
    suspend fun getAllList(): List<Record>
    @Query("select count(*) from record where date like '%' || :date")
    suspend fun getRecordsCountByDate(date: String): Int
    @Query("select * from record where date = :date order by id_record ASC")
    fun getAllStreamByDate(date: String): Flow<List<Record>>
}