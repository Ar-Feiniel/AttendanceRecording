package com.fedor.attendancerecording.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.fedor.attendancerecording.model.entity.*

@Dao
interface MarkerDao {
    @Query("select * from marker")
    fun getAll(): List<Marker>

    @Transaction
    @Query("select * from markertype")
    fun getMarkerType(): List<MarkerTypeWithMarkers>

            @Insert
    fun intertOne(marker: Marker)

    @Delete
    fun delete(marker: Marker)
}