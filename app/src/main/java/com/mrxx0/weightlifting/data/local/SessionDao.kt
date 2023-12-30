package com.mrxx0.weightlifting.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)

    @Update
    suspend fun updateSession(session: SessionEntity)

    @Delete
    suspend fun deleteSession(session: SessionEntity)

    @Query("SELECT * FROM sessions")
    suspend fun getAllSessions(): List<SessionEntity>

    @Query("SELECT * FROM sessions WHERE id = :sessionId")
    suspend fun getSessionById(sessionId: Int): SessionEntity
}

@Dao
interface ExercisesDao {
    @Insert
    suspend fun insertExercises(exercises: ExercisesEntity)

    @Update
    suspend fun updateExercises(exercises: ExercisesEntity)

    @Delete
    suspend fun deleteExercises(exercises: ExercisesEntity)

    @Query("SELECT * FROM exercises WHERE sessionId = :sessionId")
    suspend fun getExercisesForSession(sessionId: Int): List<ExercisesEntity>

    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    suspend fun getExerciseById(exerciseId: Int): ExercisesEntity
}

@Dao
interface SetsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(sets: SetsEntity)

    @Update
    suspend fun updateSets(sets: SetsEntity)

    @Delete
    suspend fun deleteSets(sets: SetsEntity)

    @Query("SELECT * FROM sets WHERE exercisesId = :exercisesId")
    suspend fun getSetsForExercises(exercisesId: Int): List<SetsEntity>
}