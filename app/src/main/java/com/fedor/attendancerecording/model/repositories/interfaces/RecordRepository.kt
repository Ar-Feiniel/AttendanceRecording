package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.MarkerType
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow

interface RecordRepository : Repositoryable<Record> {
    suspend fun getRecordsCountByDate(date: String) : Int
    fun getAllDataStreamByDate(date: String): Flow<List<Record>>
}