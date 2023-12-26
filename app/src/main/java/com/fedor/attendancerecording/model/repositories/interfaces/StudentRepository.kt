package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.dao.StudentDao
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow

interface StudentRepository : Repositoryable<Student> {
    public override fun getAllDataStream(): Flow<List<Student>>
    suspend fun getAllDataList(): List<Student>
    override fun getOneItemStreamById(id: Int): Flow<Student?>

    override suspend fun updateItem(item: Student)

    override suspend fun deleteItem(item: Student)

    override suspend fun upsertItem(item: Student)

    override suspend fun insertItem(item: Student)
}