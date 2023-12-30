package com.mrxx0.weightlifting.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mrxx0.weightlifting.data.local.exercise.ExerciseDao
import com.mrxx0.weightlifting.data.local.exercise.ExerciseEntity
import com.mrxx0.weightlifting.data.local.session.SessionDao
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetDao
import com.mrxx0.weightlifting.data.local.set.SetEntity

@Database(
    entities = [SessionEntity::class, ExerciseEntity::class, SetEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class WeightliftingDatabase : RoomDatabase() {
    abstract fun sessionDao(): SessionDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun setsDao(): SetDao
}