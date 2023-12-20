package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.MarkerDao
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import kotlinx.coroutines.flow.Flow

class OfflineMarkerRepository(private val markerDao: MarkerDao) : MarkerRepository {
    public override fun getAllDataStream(): Flow<List<Marker>> = markerDao.getAllStream()
    override fun getOneItemStreamById(id: Int): Flow<Marker?> = markerDao.getByIdStream(idMarker = id)

    override suspend fun updateItem(item: Marker) = markerDao.updateItem(item)

    override suspend fun deleteItem(item: Marker) = markerDao.deleteItem(item)

    override suspend fun insertItem(item: Marker) = markerDao.insertAll(item)

    override fun getAllDataList(): List<Marker> = markerDao.getAllDataList()

    override fun getOneItemById(id: Int): Marker = markerDao.getByIdItem(idMarker = id)

    override suspend fun upsertItem(item: Marker) = markerDao.upsertItem(item)
}