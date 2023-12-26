package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow
interface MarkerTypeRepository: Repositoryable<MarkerType> {
    override fun getAllDataStream(): Flow<List<MarkerType>>
    suspend fun getAllDataList(): List<MarkerType>
    override fun getOneItemStreamById(id: Int): Flow<MarkerType?>
    override suspend fun updateItem(item: MarkerType)
    override suspend fun deleteItem(item: MarkerType)
    override suspend fun upsertItem(item: MarkerType)
    override suspend fun insertItem(item: MarkerType)
}