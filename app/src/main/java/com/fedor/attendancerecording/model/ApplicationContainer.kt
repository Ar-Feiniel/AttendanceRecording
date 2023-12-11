package com.fedor.attendancerecording.model

import android.content.Context
import com.fedor.attendancerecording.model.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.model.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.model.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.model.repositories.offline.OfflineMarkerRepository
import com.fedor.attendancerecording.model.repositories.offline.OfflineRecordRepository
import com.fedor.attendancerecording.model.repositories.offline.OfflineStudentRepository

// app container for di
interface ApplicationContainer{
    val markerRepository: MarkerRepository
    val recordRepository: RecordRepository
    val studentRepository: StudentRepository
}

// предстваляет экземпляры репозиториев
class ApplicationDataContainer(private val context: Context) : ApplicationContainer{
    // реализация для...
    override val markerRepository: MarkerRepository by lazy{
        OfflineMarkerRepository(RecordsDB.getDataBase(context).markerDao())
    }
    override val recordRepository: RecordRepository by lazy{
        OfflineRecordRepository(RecordsDB.getDataBase(context).recordDao())
    }
    override val studentRepository: StudentRepository by lazy{
        OfflineStudentRepository(RecordsDB.getDataBase(context).studentDao())
    }
}