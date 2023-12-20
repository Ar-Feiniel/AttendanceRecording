package com.fedor.attendancerecording.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.fedor.attendancerecording.model.entity.MarkerType
import kotlinx.coroutines.flow.Flow

@Dao
interface MarkerTypeDao: DataAccessObjectable<MarkerType> {

    @Query("select * from marker_type order by id_marker_type ASC")
    override fun getAllStream(): Flow<List<MarkerType>>

    @Query("select * from marker_type order by id_marker_type ASC")
    fun getAllList(): List<MarkerType>

    @Query("select * from marker_type where id_marker_type = :idMarkerType")
    fun getByIdStream(idMarkerType: Int) : Flow<MarkerType>

    @Query("select * from marker_type where id_marker_type = :idMarkerType")
    fun getByIdItem(idMarkerType: Int) : MarkerType

    @Query("select * from marker_type where name = :name")
    fun getByItemName(name: String) : MarkerType

    @Insert(onConflict  = OnConflictStrategy.ABORT)
    override fun insertAll(vararg markerTypes: MarkerType)

    @Upsert
    override suspend fun upsertItem(markerType: MarkerType)

    @Delete
    override suspend fun deleteItem(markerType: MarkerType)
}