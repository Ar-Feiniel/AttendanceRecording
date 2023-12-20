package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.MarkerTypeDao
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerTypeRepository
import kotlinx.coroutines.flow.Flow

class OfflineMarkerTypeRepository(private val markerTypeDao: MarkerTypeDao) : MarkerTypeRepository {
    override fun getAllDataStream(): Flow<List<MarkerType>> = markerTypeDao.getAllStream()
    override fun getAllDataList(): List<MarkerType> = markerTypeDao.getAllList()
    override fun getOneItemStreamById(id: Int): Flow<MarkerType?> = markerTypeDao.getByIdStream(idMarkerType = id)
    override fun getOneItemById(id: Int): MarkerType = markerTypeDao.getByIdItem(idMarkerType = id)

    override suspend fun updateItem(item: MarkerType) = markerTypeDao.updateItem(item)

    override suspend fun deleteItem(item: MarkerType) = markerTypeDao.deleteItem(item)

    override suspend fun upsertItem(item: MarkerType) = markerTypeDao.deleteItem(item)

    override suspend fun insertItem(item: MarkerType) = markerTypeDao.insertAll(item)

    override fun getOneItemByName(name: String): MarkerType = markerTypeDao.getByItemName(name)

}