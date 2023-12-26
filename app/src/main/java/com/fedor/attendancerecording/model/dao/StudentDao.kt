package com.fedor.attendancerecording.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.fedor.attendancerecording.model.entity.Record
import com.fedor.attendancerecording.model.entity.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao: DataAccessObjectable<Student> {

    @Query("select * from student order by id_student ASC")
    override fun getAllStream(): Flow<List<Student>>
    @Query("select * from student order by id_student ASC")
    suspend fun getAllList(): List<Student>
    @Query("select * from student where id_student = :id")
    fun getById(id: Int): Flow<Student>
    @Insert(onConflict  = OnConflictStrategy.ABORT)
    override fun insertAll(vararg students: Student)
    @Upsert
    override suspend fun upsertItem(student: Student)
    @Delete
    override suspend fun deleteItem(student: Student)
}