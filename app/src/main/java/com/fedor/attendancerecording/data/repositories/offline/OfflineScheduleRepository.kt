package com.fedor.attendancerecording.data.repositories.offline

import com.fedor.attendancerecording.data.dao.ScheduleDao
import com.fedor.attendancerecording.data.entity.Schedule
import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val scheduleDao: ScheduleDao) : ScheduleRepository {
    override suspend fun getItemByDate(date: String): Schedule = scheduleDao.getScheduleByDate(date = date)

    override fun getAllDataStream(): Flow<List<Schedule>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDataList(): List<Schedule> {
        TODO("Not yet implemented")
    }

    override fun getItemStreamById(id: Int): Flow<Schedule?> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemById(id: Int): Schedule? {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(schedule: Schedule) =scheduleDao.updateItem(schedule)
    override suspend fun deleteItem(schedule: Schedule) = scheduleDao.deleteItem(schedule)
    override suspend fun insertItem(schedule: Schedule) = scheduleDao.insertAll(schedule)
    override suspend fun upsertItem(schedule: Schedule) = scheduleDao.upsertItem(schedule)
}