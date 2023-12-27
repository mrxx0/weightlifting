package com.mrxx0.weightlifting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SessionEntity::class, exercisesEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SessionDatabase : RoomDatabase() {
    abstract val dao: SessionDao
}