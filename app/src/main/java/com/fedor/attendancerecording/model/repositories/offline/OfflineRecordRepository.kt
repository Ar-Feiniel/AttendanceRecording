package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.RecordDao
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.repositories.interfaces.RecordRepository
import kotlinx.coroutines.flow.Flow

class OfflineRecordRepository(private val recordDao: RecordDao) : RecordRepository {
    override fun getAllDataStream(): Flow<List<Record>> = recordDao.getAllStream()
    override suspend fun getAllDataList(): List<Record> = recordDao.getAllList()
    override fun getOneItemStreamById(id: Int): Flow<Record?> {
        TODO("Not yet implemented")
    }
    override fun getAllDataStreamByDate(date: String): Flow<List<Record>> = recordDao.getAllStreamByDate(date = date)
    override suspend fun updateItem(item: Record) {
        TODO("Not yet implemented")
    }
    override suspend fun deleteItem(item: Record) {
        TODO("Not yet implemented")
    }
    override suspend fun insertItem(item: Record) {
        TODO("Not yet implemented")
    }
    override suspend fun upsertItem(item: Record) = recordDao.upsertItem(item)
}