package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.Record
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