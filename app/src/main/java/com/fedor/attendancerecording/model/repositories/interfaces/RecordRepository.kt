package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow

interface RecordRepository : Repositoryable<Record> {
    public override fun getAllDataStream(): Flow<List<Record>>
    override fun getOneItemStream(id: Int): Flow<Record?>

    override suspend fun updateItem(item: Record)

    override suspend fun deleteItem(item: Record)

    override suspend fun insertItem(item: Record)
}