package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerDao : DataAccessObjectable<Marker> {

    @Query("select * from marker order by id_marker ASC")
    fun getAllStream(): Flow<List<Marker>>
    @Query("select * from marker order by id_marker ASC")
    suspend fun getAllList(): List<Marker>
    @Query("select * from marker where id_marker = :idMarker")
    fun getByIdStream(idMarker: Int): Flow<Marker>
    @Query("select * from marker where id_marker = :idMarker")
    suspend fun getById(idMarker: Int): Marker?
}