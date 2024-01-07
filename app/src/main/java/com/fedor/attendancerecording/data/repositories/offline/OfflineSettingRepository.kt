package com.fedor.attendancerecording.data.repositories.offline

import com.fedor.attendancerecording.data.dao.SettingDao
import com.fedor.attendancerecording.data.entity.Setting
import com.fedor.attendancerecording.data.repositories.interfaces.SettingRepository
import kotlinx.coroutines.flow.Flow

class OfflineSettingRepository(private val settingDao: SettingDao) : SettingRepository {
    override fun getAllDataStream(): Flow<List<Setting>> {
        TODO("Not yet implemented")
    }
    override suspend fun getAllDataList(): List<Setting> = settingDao.getAllList()
    override fun getItemStreamById(id: Int): Flow<Setting?> {
        TODO("Not yet implemented")
    }
    override suspend fun getItemById(id: Int): Setting? {
        TODO("Not yet implemented")
    }
    override suspend fun insertItem(item: Setting) {
        TODO("Not yet implemented")
    }
    override suspend fun updateItem(item: Setting) = settingDao.updateItem(item = item)
    override suspend fun upsertItem(item: Setting) {
        TODO("Not yet implemented")
    }
    override suspend fun deleteItem(item: Setting) {
        TODO("Not yet implemented")
    }
}