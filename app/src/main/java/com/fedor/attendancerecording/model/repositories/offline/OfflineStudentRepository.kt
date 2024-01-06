package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.StudentDao
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.Flow

class OfflineStudentRepository(private val studentDao: StudentDao) : StudentRepository {
    override fun getAllDataStream(): Flow<List<Student>> = studentDao.getAllStream()
    override suspend fun getAllDataList(): List<Student> = studentDao.getAllList()
    override fun getItemStreamById(id: Int): Flow<Student?> = studentDao.getById(id)
    override suspend fun getItemById(id: Int): Student? {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(student: Student) =studentDao.updateItem(student)
    override suspend fun deleteItem(student: Student) = studentDao.deleteItem(student)
    override suspend fun insertItem(student: Student) = studentDao.insertAll(student)
    override suspend fun upsertItem(student: Student) = studentDao.upsertItem(student)
}