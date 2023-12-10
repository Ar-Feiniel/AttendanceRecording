package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerTypeWithMarkers

interface DataAccessObjectable<T> {
    public fun getAll(): LiveData<List<T>>
    fun insertOne(item: T)
    fun update(item: T)
    fun delete(item: T)
}