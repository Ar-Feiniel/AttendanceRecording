package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.entity.MarkerTypeWithMarkers

@Dao
interface MarkerTypeDao: DataAccessObjectable<MarkerType> {
    @Query("select * from marker_type order by id_marker_type ASC")
    override fun getAll(): LiveData<List<MarkerType>>

    @Transaction
    @Insert(onConflict  = OnConflictStrategy.ABORT)
    override fun insertOne(markerType: MarkerType)

    @Transaction
    @Delete
    override fun delete(markerType: MarkerType)
}