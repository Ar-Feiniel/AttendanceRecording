package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.RecordDao
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.repositories.interfaces.RecordRepository
import kotlinx.coroutines.flow.Flow

class OfflineRecordRepository(private val recordDao: RecordDao) : RecordRepository {
    public override fun getAllDataStream(): Flow<List<Record>> = recordDao.getAllStream()
    override fun getOneItemStreamById(id: Int): Flow<Record?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Record) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(item: Record) {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: Record) {
        TODO("Not yet implemented")
    }

    override fun getAllDataList(): List<Record> {
        TODO("Not yet implemented")
    }

    override fun getOneItemById(id: Int): Record {
        TODO("Not yet implemented")
    }

    override suspend fun upsertItem(item: Record) {
        TODO("Not yet implemented")
    }
}