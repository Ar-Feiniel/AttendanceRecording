package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.Schedule

@Dao
interface ScheduleDao: DataAccessObjectable<Schedule> {
    @Query("select * from schedule where date = :date")
    suspend fun getScheduleByDate(date: String): Schedule
}