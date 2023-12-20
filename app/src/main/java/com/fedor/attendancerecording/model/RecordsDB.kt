package com.fedor.attendancerecording.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fedor.attendancerecording.model.dao.*
import com.fedor.attendancerecording.model.entity.*

import java.util.concurrent.Executors
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()
fun ioThread(f : () -> Unit) {
    IO_EXECUTOR.execute(f)
}
@Database (entities = [Student::class, Marker::class, Record::class, MarkerType::class, NonWorkingDay::class], version = 1, exportSchema = false)
abstract class RecordsDB : RoomDatabase() {

    abstract fun studentDao(): StudentDao
    abstract fun markerDao(): MarkerDao
    abstract fun recordDao(): RecordDao
    abstract fun markerTypeDao(): MarkerTypeDao
    abstract fun nonWorkingDayDao(): ScheduleDao

    companion object {
        // db singleton
        @Volatile
        private var instance: RecordsDB? = null

        public fun getDataBase(context: Context): RecordsDB {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordsDB::class.java,
                    "records_db"
                ).addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        ioThread {
                            val markerTypeDao = instance?.markerTypeDao()
                            val markerDao = instance?.markerDao()
                            markerTypeDao?.insertAll(
                                MarkerType(0, "Уважительная", null),
                                MarkerType(0, "Неуважительная", null)
                            )
                            markerDao?.insertAll(
                                Marker(0, "2", 1),
                                Marker(0, "2у", 1),
                                Marker(0, "2б", 1),
                                Marker(0, "1", 1),
                                Marker(0, "1у", 1),
                                Marker(0, "1б", 1)
                            )
                        }
                    }
                }).build()
                instance = newInstance
                return newInstance
            }
        }
    }
}