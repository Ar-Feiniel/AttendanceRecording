package com.fedor.attendancerecording.data

import android.content.Context
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerRepository
import com.fedor.attendancerecording.data.repositories.interfaces.MarkerTypeRepository
import com.fedor.attendancerecording.data.repositories.interfaces.RecordRepository
import com.fedor.attendancerecording.data.repositories.interfaces.ScheduleRepository
import com.fedor.attendancerecording.data.repositories.interfaces.SettingRepository
import com.fedor.attendancerecording.data.repositories.interfaces.StudentRepository
import com.fedor.attendancerecording.data.repositories.offline.OfflineMarkerRepository
import com.fedor.attendancerecording.data.repositories.offline.OfflineMarkerTypeRepository
import com.fedor.attendancerecording.data.repositories.offline.OfflineRecordRepository
import com.fedor.attendancerecording.data.repositories.offline.OfflineScheduleRepository
import com.fedor.attendancerecording.data.repositories.offline.OfflineSettingRepository
import com.fedor.attendancerecording.data.repositories.offline.OfflineStudentRepository

// app container for di
interface ApplicationContainer{
    val markerRepository: MarkerRepository
    val markerTypeRepository: MarkerTypeRepository
    val recordRepository: RecordRepository
    val studentRepository: StudentRepository
    val settingRepository: SettingRepository
    val scheduleRepository: ScheduleRepository
}

// предстваляет экземпляры репозиториев
class ApplicationDataContainer(private val context: Context) : ApplicationContainer{
    // реализация для...
    override val markerRepository: MarkerRepository by lazy{
        OfflineMarkerRepository(RecordsDB.getDataBase(context).markerDao())
    }
    override val markerTypeRepository: MarkerTypeRepository by lazy{
        OfflineMarkerTypeRepository(RecordsDB.getDataBase(context).markerTypeDao())
    }
    override val recordRepository: RecordRepository by lazy{
        OfflineRecordRepository(RecordsDB.getDataBase(context).recordDao())
    }
    override val studentRepository: StudentRepository by lazy{
        OfflineStudentRepository(RecordsDB.getDataBase(context).studentDao())
    }
    override val settingRepository: SettingRepository by lazy{
        OfflineSettingRepository(RecordsDB.getDataBase(context).settingDao())
    }
    override val scheduleRepository: ScheduleRepository by lazy{
        OfflineScheduleRepository(RecordsDB.getDataBase(context).scheduleDao())
    }
}