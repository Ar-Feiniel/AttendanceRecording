package com.fedor.attendancerecording.data.repositories.offline

import com.fedor.attendancerecording.data.dao.ScheduleDayDao
import com.fedor.attendancerecording.data.entity.ScheduleDay
import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import kotlinx.coroutines.flow.Flow

class OfflineScheduleRepository(private val scheduleDao: ScheduleDayDao) : ScheduleRepository {
    override suspend fun getItemByDate(date: String): ScheduleDay = scheduleDao.getScheduleByDate(date = date)
    override suspend fun getListByMonth(month: Int, year: Int): List<ScheduleDay> =
        scheduleDao.getScheduleListByMonthYear(month = month, year = year)

    override fun getAllDataStream(): Flow<List<ScheduleDay>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDataList(): List<ScheduleDay> {
        TODO("Not yet implemented")
    }

    override fun getItemStreamById(id: Int): Flow<ScheduleDay?> {
        TODO("Not yet implemented")
    }

    override suspend fun getItemById(id: Int): ScheduleDay? {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(item: ScheduleDay) =scheduleDao.updateItem(item)
    override suspend fun deleteItem(item: ScheduleDay) = scheduleDao.deleteItem(item)
    override suspend fun insertItem(item: ScheduleDay) = scheduleDao.insertAll(item)
    override suspend fun upsertItem(item: ScheduleDay) = scheduleDao.upsertItem(item)
}