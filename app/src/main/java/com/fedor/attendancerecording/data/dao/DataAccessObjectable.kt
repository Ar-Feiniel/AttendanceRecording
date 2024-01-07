package com.fedor.attendancerecording.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Upsert

interface DataAccessObjectable<T> {
    @Insert(onConflict  = OnConflictStrategy.ABORT)
    fun insertAll(vararg items: T)
    @Update
    suspend fun updateItem(item: T)
    @Upsert
    suspend fun upsertItem(item: T)
    @Delete
    suspend fun deleteItem(item: T)
}