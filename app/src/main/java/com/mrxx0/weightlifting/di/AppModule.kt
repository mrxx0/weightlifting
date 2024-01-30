package com.mrxx0.weightlifting.di

import android.content.Context
import androidx.room.Room
import com.mrxx0.weightlifting.data.local.WeightliftingDatabase
import com.mrxx0.weightlifting.data.repository.ExerciseModelRepositoryImpl
import com.mrxx0.weightlifting.data.repository.ExerciseRepositoryImpl
import com.mrxx0.weightlifting.data.repository.SessionRepositoryImpl
import com.mrxx0.weightlifting.data.repository.SetRepositoryImpl
import com.mrxx0.weightlifting.domain.repository.ExerciseModelRepository
import com.mrxx0.weightlifting.domain.repository.ExerciseRepository
import com.mrxx0.weightlifting.domain.repository.SessionRepository
import com.mrxx0.weightlifting.domain.repository.SetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideWeightliftingDatabase(@ApplicationContext context: Context): WeightliftingDatabase {
        return Room.databaseBuilder(
            context,
            WeightliftingDatabase::class.java,
            "sessions.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSessionRepository(weightliftingDatabase: WeightliftingDatabase): SessionRepository {
        return SessionRepositoryImpl(weightliftingDatabase.sessionDao())
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(weightliftingDatabase: WeightliftingDatabase): ExerciseRepository {
        return ExerciseRepositoryImpl(weightliftingDatabase.exerciseDao())
    }

    @Provides
    @Singleton
    fun provideSetRepository(weightliftingDatabase: WeightliftingDatabase): SetRepository {
        return SetRepositoryImpl(weightliftingDatabase.setsDao())
    }

    @Provides
    @Singleton
    fun provideExerciseModelRepository(weightliftingDatabase: WeightliftingDatabase): ExerciseModelRepository {
        return ExerciseModelRepositoryImpl(weightliftingDatabase.exerciseModelDao())
    }
}