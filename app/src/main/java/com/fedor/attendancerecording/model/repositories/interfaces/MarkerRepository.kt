package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow
interface MarkerRepository: Repositoryable<Marker> {
    override fun getAllDataStream(): Flow<List<Marker>>

    override fun getOneItemStreamById(id: Int): Flow<Marker?>

    override suspend fun updateItem(item: Marker)

    override suspend fun deleteItem(item: Marker)

    override suspend fun upsertItem(item: Marker)

    override suspend fun insertItem(item: Marker)
}