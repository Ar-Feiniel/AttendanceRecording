package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao: DataAccessObjectable<Student> {
    @Query("select * from student order by id_student ASC")
    override fun getAll(): Flow<List<Student>>

    @Transaction
    @Insert(onConflict  = OnConflictStrategy.ABORT)
    override suspend fun insertOne(student: Student)

    @Transaction
    @Delete
    override suspend fun delete(student: Student)
}