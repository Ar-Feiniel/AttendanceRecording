package com.fedor.attendancerecording.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.fedor.attendancerecording.data.entity.Setting

@Dao
interface SettingDao: DataAccessObjectable<Setting> {
    @Query("select * from settings order by id_setting")
    suspend fun getAllList(): List<Setting>
}