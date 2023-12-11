package com.fedor.attendancerecording.model.dao

import kotlinx.coroutines.flow.Flow

interface DataAccessObjectable<T> {
    public fun getAll(): Flow<List<T>>
    suspend fun insertOne(item: T)
    suspend fun update(item: T)
    suspend fun delete(item: T)
}