package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow
interface MarkerRepository: Repositoryable<Marker> {
    override fun getAllDataStream(): Flow<List<Marker>>

    override fun getOneItemStream(id: Int): Flow<Marker?>

    override suspend fun updateItem(item: Marker)

    override suspend fun deleteItem(item: Marker) {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: Marker) {
        TODO("Not yet implemented")
    }
}