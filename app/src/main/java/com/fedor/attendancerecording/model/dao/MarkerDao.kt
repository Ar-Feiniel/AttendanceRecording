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

@Dao
interface MarkerDao : DataAccessObjectable<Marker> {
    @Query("select * from marker order by id_marker ASC")
    override fun getAll(): LiveData<List<Marker>>

    @Transaction
    @Insert(onConflict  = ABORT)
    override fun insertOne(marker: Marker)

    @Transaction
    @Delete
    override fun delete(marker: Marker)
}