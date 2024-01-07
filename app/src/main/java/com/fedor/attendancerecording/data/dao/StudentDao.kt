package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao: DataAccessObjectable<Student> {
    @Query("select * from student order by id_student ASC")
    fun getAllStream(): Flow<List<Student>>
    @Query("select * from student order by id_student ASC")
    suspend fun getAllList(): List<Student>
    @Query("select * from student where id_student = :id")
    fun getById(id: Int): Flow<Student>
}