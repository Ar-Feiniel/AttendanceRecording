package com.fedor.attendancerecording.data.repositories.interfaces

import com.fedor.attendancerecording.data.entity.ScheduleDay
import com.fedor.attendancerecording.data.repositories.Repositoryable

interface ScheduleRepository: Repositoryable<ScheduleDay> {
    suspend fun getItemByDate(date: String): ScheduleDay
    suspend fun getListByMonth(month: Int, year: Int): List<ScheduleDay>
}