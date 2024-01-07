package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.dao.DataAccessObjectable
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Schedule
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow
interface ScheduleRepository: Repositoryable<Schedule> {
    suspend fun getItemByDate(date: String): Schedule
}