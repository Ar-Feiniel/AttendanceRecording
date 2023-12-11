package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.ABORT
import androidx.room.OnConflictStrategy.Companion.ROLLBACK
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fedor.attendancerecording.model.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerDao : DataAccessObjectable<Marker> {
    @Query("select * from marker order by id_marker ASC")
    override fun getAll(): Flow<List<Marker>>

    @Transaction
    @Insert(onConflict  = ABORT)
    override suspend fun insertOne(marker: Marker)

    @Transaction
    @Delete
    override suspend fun delete(marker: Marker)
}