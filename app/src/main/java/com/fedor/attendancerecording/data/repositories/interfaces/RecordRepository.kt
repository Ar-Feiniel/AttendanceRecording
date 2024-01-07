package com.fedor.attendancerecording.data.repositories.interfaces

import com.fedor.attendancerecording.data.entity.Record
import com.fedor.attendancerecording.data.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow

interface RecordRepository : Repositoryable<Record> {
    suspend fun getRecordsCountByDate(date: String) : Int
    fun getAllDataStreamByDate(date: String): Flow<List<Record>>
}