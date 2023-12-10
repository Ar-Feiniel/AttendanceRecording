package com.fedor.attendancerecording.model.repositories

import androidx.lifecycle.LiveData
import com.fedor.attendancerecording.model.dao.MarkerDao
import com.fedor.attendancerecording.model.entity.Marker

class MarkerRepository(private val markerDao: MarkerDao) : Repositoryable<Marker> {
    public override val readAllData: LiveData<List<Marker>> = markerDao.getAll()
    override suspend fun deleteItem(item: Marker) {
        TODO("Not yet implemented")
    }

    override suspend fun addItem(item: Marker) {
        TODO("Not yet implemented")
    }
}