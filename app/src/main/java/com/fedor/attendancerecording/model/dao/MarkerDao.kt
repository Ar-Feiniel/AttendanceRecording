package com.fedor.attendancerecording.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.ABORT
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.fedor.attendancerecording.model.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerDao : DataAccessObjectable<Marker> {

    @Query("select * from marker order by id_marker ASC")
    override fun getAllStream(): Flow<List<Marker>>

    @Query("select * from marker where id_marker = :idMarker")
    fun getByIdStream(idMarker: Int): Flow<Marker>

    @Insert(onConflict  = ABORT)
    override fun insertAll(vararg markers: Marker)

    @Upsert
    override suspend fun upsertItem(marker: Marker)

    @Delete
    override suspend fun deleteItem(marker: Marker)
}