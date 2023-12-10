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

@Dao
interface StudentDao: DataAccessObjectable<Student> {
    @Query("select * from student order by id_student ASC")
    override fun getAll(): LiveData<List<Student>>

    @Transaction
    @Insert(onConflict  = OnConflictStrategy.ABORT)
    override fun insertOne(student: Student)

    @Transaction
    @Delete
    override fun delete(student: Student)
}