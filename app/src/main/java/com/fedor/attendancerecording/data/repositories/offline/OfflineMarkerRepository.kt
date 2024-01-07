package com.fedor.attendancerecording.data.repositories.offline

import com.fedor.attendancerecording.data.dao.MarkerDao
import com.fedor.attendancerecording.data.entity.Marker
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerRepository
import kotlinx.coroutines.flow.Flow

class OfflineMarkerRepository(private val markerDao: MarkerDao) : MarkerRepository {
    override fun getAllDataStream(): Flow<List<Marker>> = markerDao.getAllStream()
    override suspend fun getAllDataList(): List<Marker> = markerDao.getAllList()
    override fun getItemStreamById(id: Int): Flow<Marker?> = markerDao.getByIdStream(idMarker = id)
    override suspend fun getItemById(id: Int): Marker? = markerDao.getById(idMarker = id)
    override suspend fun updateItem(item: Marker) = markerDao.updateItem(item)
    override suspend fun deleteItem(item: Marker) = markerDao.deleteItem(item)
    override suspend fun insertItem(item: Marker) = markerDao.insertAll(item)
    override suspend fun upsertItem(item: Marker) = markerDao.upsertItem(item)
}