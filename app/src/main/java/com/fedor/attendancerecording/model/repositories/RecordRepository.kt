package com.fedor.attendancerecording.model.repositories

import androidx.lifecycle.LiveData
import com.fedor.attendancerecording.model.dao.RecordDao
import com.fedor.attendancerecording.model.entity.Record

class RecordRepository(private val recordDao: RecordDao) : Repositoryable<Record> {
    public override val readAllData: LiveData<List<Record>> = recordDao.getAll()
    override suspend fun deleteItem(item: Record) {
        TODO("Not yet implemented")
    }

    override suspend fun addItem(item: Record) {
        TODO("Not yet implemented")
    }
}