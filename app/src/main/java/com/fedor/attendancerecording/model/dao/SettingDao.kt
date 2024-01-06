package com.fedor.attendancerecording.model.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.model.entity.Setting

@Dao
interface SettingDao: DataAccessObjectable<Setting> {
    @Query("select * from settings order by id_setting")
    suspend fun getAllList(): List<Setting>
}