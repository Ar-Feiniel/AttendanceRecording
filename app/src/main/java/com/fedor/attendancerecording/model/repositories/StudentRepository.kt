package com.fedor.attendancerecording.model.repositories

import androidx.lifecycle.LiveData
import com.fedor.attendancerecording.model.dao.StudentDao
import com.fedor.attendancerecording.model.entity.Student

class StudentRepository(private val studentDao: StudentDao) : Repositoryable<Student> {
    public override val readAllData: LiveData<List<Student>> = studentDao.getAll()
    override suspend fun deleteItem(item: Student) {
        TODO("Not yet implemented")
    }

    override suspend fun addItem(item: Student) {
        TODO("Not yet implemented")
    }
}