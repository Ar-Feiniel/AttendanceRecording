package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.MarkerDao
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import kotlinx.coroutines.flow.Flow

class OfflineMarkerRepository(private val markerDao: MarkerDao) : MarkerRepository {
    public override fun getAllDataStream(): Flow<List<Marker>> = markerDao.getAll()
    override fun getOneItemStream(id: Int): Flow<Marker?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Marker) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(item: Marker) {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: Marker) {
        TODO("Not yet implemented")
    }
}