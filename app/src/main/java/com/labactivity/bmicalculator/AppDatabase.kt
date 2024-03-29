package com.labactivity.bmicalculator

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Record::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recordDao() : RecordDao
}