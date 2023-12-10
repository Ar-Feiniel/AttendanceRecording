package com.fedor.attendancerecording.model.repositories

import androidx.lifecycle.LiveData
import com.fedor.attendancerecording.model.entity.Record

interface Repositoryable<T> {
    public val readAllData: LiveData<List<T>>
    suspend fun addItem(item: T)
    suspend fun deleteItem(item: T)
}