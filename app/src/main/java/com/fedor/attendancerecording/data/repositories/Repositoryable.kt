package com.fedor.attendancerecording.data.repositories

import kotlinx.coroutines.flow.Flow

interface Repositoryable<T> {
    fun getAllDataStream(): Flow<List<T>>
    suspend fun getAllDataList(): List<T>
    fun getItemStreamById(id: Int): Flow<T?>
    suspend fun getItemById(id: Int): T?
    suspend fun insertItem(item: T)
    suspend fun updateItem(item: T)
    suspend fun upsertItem(item: T)
    suspend fun deleteItem(item: T)
}