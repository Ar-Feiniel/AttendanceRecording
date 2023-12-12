package com.fedor.attendancerecording.model.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface DataAccessObjectable<T> {
    @Query("")
    public fun getAll(): Flow<List<T>>

    @Insert
    suspend fun insertOne(item: T)
    @Update
    suspend fun update(item: T)
    @Delete
    suspend fun delete(item: T)
}