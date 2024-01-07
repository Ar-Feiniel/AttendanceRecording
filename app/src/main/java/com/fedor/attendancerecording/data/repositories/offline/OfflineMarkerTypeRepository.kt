package com.fedor.attendancerecording.data.repositories.offline

import com.fedor.attendancerecording.data.dao.MarkerTypeDao
import com.fedor.attendancerecording.data.entity.MarkerType
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerTypeRepository
import kotlinx.coroutines.flow.Flow

class OfflineMarkerTypeRepository(private val markerTypeDao: MarkerTypeDao) : MarkerTypeRepository {
    override fun getAllDataStream(): Flow<List<MarkerType>> = markerTypeDao.getAllStream()
    override suspend fun getAllDataList(): List<MarkerType> = markerTypeDao.getAllList()
    override fun getItemStreamById(id: Int): Flow<MarkerType?> = markerTypeDao.getByIdStream(idMarkerType = id)
    override suspend fun getItemById(id: Int): MarkerType? {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(item: MarkerType) = markerTypeDao.updateItem(item)
    override suspend fun deleteItem(item: MarkerType) = markerTypeDao.deleteItem(item)
    override suspend fun upsertItem(item: MarkerType) = markerTypeDao.deleteItem(item)
    override suspend fun insertItem(item: MarkerType) = markerTypeDao.insertAll(item)
}