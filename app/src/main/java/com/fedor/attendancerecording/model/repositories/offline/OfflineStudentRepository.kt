package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.StudentDao
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.Repositoryable
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import kotlinx.coroutines.flow.Flow

class OfflineStudentRepository(private val studentDao: StudentDao) : StudentRepository {
    public override fun getAllDataStream(): Flow<List<Student>> = studentDao.getAll()
    override fun getOneItemStream(id: Int): Flow<Student?> {
        TODO("Not yet implemented")
    }

    override suspend fun updateItem(item: Student) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(item: Student) {
        TODO("Not yet implemented")
    }

    override suspend fun insertItem(item: Student) {
        TODO("Not yet implemented")
    }
}