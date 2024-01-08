package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.ScheduleDay

@Dao
interface ScheduleDayDao: DataAccessObjectable<ScheduleDay> {

    @Query("select * from schedule_day where date like '%' || :month || '.' || :year order by id_schedule_day ASC")
    suspend fun getScheduleListByMonthYear(month: Int, year: Int): List<ScheduleDay>

    @Query("select * from schedule_day where date = :date")
    suspend fun getScheduleByDate(date: String): ScheduleDay?
}