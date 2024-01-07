package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.MarkerType
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerTypeDao: DataAccessObjectable<MarkerType> {

    @Query("select * from marker_type order by id_marker_type ASC")
    fun getAllStream(): Flow<List<MarkerType>>
    @Query("select * from marker_type order by id_marker_type ASC")
    suspend fun getAllList(): List<MarkerType>
    @Query("select * from marker_type where id_marker_type = :idMarkerType")
    fun getByIdStream(idMarkerType: Int) : Flow<MarkerType>
    @Query("select * from marker_type where name = :name")
    fun getByItemName(name: String) : MarkerType
}