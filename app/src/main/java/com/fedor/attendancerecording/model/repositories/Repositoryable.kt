package com.fedor.attendancerecording.model.repositories

import kotlinx.coroutines.flow.Flow

interface Repositoryable<T> {
    public fun getAllDataStream(): Flow<List<T>>
    public fun getOneItemStreamById(id: Int): Flow<T?>
    suspend fun insertItem(item: T)
    suspend fun updateItem(item: T)
    suspend fun upsertItem(item: T)
    suspend fun deleteItem(item: T)
}