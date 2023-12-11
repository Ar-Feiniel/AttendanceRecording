package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.ABORT
import androidx.room.Query
import androidx.room.Transaction
import com.fedor.attendancerecording.model.entity.Record
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao: DataAccessObjectable<Record> {
    @Query("select * from record order by id_record ASC")
    override fun getAll(): Flow<List<Record>>

    @Transaction
    @Insert(onConflict  = ABORT)
    override suspend fun insertOne(record: Record)

    @Transaction
    @Delete
    override suspend fun delete(record: Record)
}