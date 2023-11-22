package com.fedor.attendancerecording.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fedor.attendancerecording.model.entity.*
@Database (entities = [Marker::class], version = 1)
abstract class RecordsDB : RoomDatabase() {
    companion object{
        fun getDB(context: Context): RecordsDB {
            return  Room.databaseBuilder(
                context.applicationContext,
                RecordsDB::class.java,
                "records.db"
            ).build()
        }
    }
}