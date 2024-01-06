package com.fedor.attendancerecording.model.repositories.offline

import com.fedor.attendancerecording.model.dao.RecordDao
import com.fedor.attendancerecording.model.dao.SettingDao
import com.fedor.attendancerecording.model.entity.Setting
import com.fedor.attendancerecording.model.repositories.interfaces.SettingRepository
import kotlinx.coroutines.flow.Flow

class OfflineSettingRepository(private val settingDao: SettingDao) : SettingRepository {
    override fun getAllDataStream(): Flow<List<Setting>> {
        TODO("Not yet implemented")
    }
    override suspend fun getAllDataList(): List<Setting> {
        TODO("Not yet implemented")
    }
    override fun getItemStreamById(id: Int): Flow<Setting?> {
        TODO("Not yet implemented")
    }
    override suspend fun getItemById(id: Int): Setting? {
        TODO("Not yet implemented")
    }
    override suspend fun insertItem(item: Setting) {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(item: Setting) {
        TODO("Not yet implemented")
    }
    override suspend fun upsertItem(item: Setting) {
        TODO("Not yet implemented")
    }
    override suspend fun deleteItem(item: Setting) {
        TODO("Not yet implemented")
    }
}