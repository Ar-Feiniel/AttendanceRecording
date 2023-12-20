package com.fedor.attendancerecording.model.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

interface DataAccessObjectable<T> {
    @Query("")
    fun getAll(): Flow<List<T>>

    @Upsert
    suspend fun upsertItem(item: T)

    @Insert(onConflict  = OnConflictStrategy.ABORT)
    suspend fun insertOne(item: T)

    @Update
    suspend fun update(item: T)

    @Delete
    suspend fun delete(item: T)
}