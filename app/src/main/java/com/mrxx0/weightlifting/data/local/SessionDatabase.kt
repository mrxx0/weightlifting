package com.mrxx0.weightlifting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [SessionEntity::class, ExercisesEntity::class, SetsEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SessionDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun exercisesDao(): ExercisesDao
    abstract fun setsDao(): SetsDao
}