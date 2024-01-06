package com.fedor.attendancerecording.model.repositories.interfaces

import com.fedor.attendancerecording.model.dao.StudentDao
import com.fedor.attendancerecording.model.entity.Marker
import com.fedor.attendancerecording.model.entity.Student
import com.fedor.attendancerecording.model.repositories.Repositoryable
import kotlinx.coroutines.flow.Flow

interface StudentRepository : Repositoryable<Student> {

}