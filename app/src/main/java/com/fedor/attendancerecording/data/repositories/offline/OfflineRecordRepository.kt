package com.fedor.attendancerecording.data.repositories.offline

import com.fedor.attendancerecording.data.dao.RecordDao
import com.fedor.attendancerecording.data.entity.Record
import com.fedor.attendancerecording.data.repositories.interfaces.RecordRepository
import kotlinx.coroutines.flow.Flow

class OfflineRecordRepository(private val recordDao: RecordDao) : RecordRepository {
    override fun getAllDataStream(): Flow<List<Record>> = recordDao.getAllStream()
    override suspend fun getAllDataList(): List<Record> = recordDao.getAllList()
    override suspend fun getRecordsCountByDate(date: String): Int = recordDao.getRecordsCountByDate(date)
    override fun getItemStreamById(id: Int): Flow<Record?> {
        TODO("Not yet implemented")
    }
    override suspend fun getItemById(id: Int): Record? {
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