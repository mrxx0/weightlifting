package com.mrxx0.weightlifting.di

import android.content.Context
import androidx.room.Room
import com.mrxx0.weightlifting.data.local.SessionDatabase
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
    fun provideSessionDatabase(@ApplicationContext context: Context): SessionDatabase {
        return Room.databaseBuilder(
            context,
            SessionDatabase::class.java,
            "sessions.db"
        ).build()
    }
}