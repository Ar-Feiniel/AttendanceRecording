package com.fedor.attendancerecording.data.repositories.interfaces

import com.fedor.attendancerecording.data.entity.Schedule
import com.fedor.attendancerecording.data.repositories.Repositoryable

interface ScheduleRepository: Repositoryable<Schedule> {
    suspend fun getItemByDate(date: String): Schedule
}