package com.fedor.attendancerecording.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fedor.attendancerecording.model.dao.MarkerDao
import com.fedor.attendancerecording.model.dao.MarkerTypeDao
import com.fedor.attendancerecording.model.dao.NonWorkingDayDao
import com.fedor.attendancerecording.model.dao.RecordDao
import com.fedor.attendancerecording.model.dao.StudentDao
import com.fedor.attendancerecording.model.entity.*
@Database (entities = [Student::class, Marker::class, Record::class, MarkerType::class, NonWorkingDay::class], version = 1, exportSchema = false)
abstract class RecordsDB : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun markerDao(): MarkerDao
    abstract fun recordDao(): RecordDao
    abstract fun markerTypeDao(): MarkerTypeDao
    abstract fun nonWorkingDayDao(): NonWorkingDayDao

    companion object {
        // db singleton
        @Volatile
        private var instance: RecordsDB? = null

        public fun getDataBase(context: Context): RecordsDB{
            val tempInstance = instance
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordsDB::class.java,
                    "records.db"
                ).build()
                instance = newInstance
                return newInstance
            }
        }
// times old verions // uncomment if not working
//        private fun getDB(context: Context): RecordsDB {
//            return  Room.databaseBuilder(
//                context.applicationContext,
//                RecordsDB::class.java,
//                "records.db"
//            ).build()
//        }
    }
}