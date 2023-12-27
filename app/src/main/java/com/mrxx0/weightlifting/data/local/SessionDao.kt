package com.mrxx0.weightlifting.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface SessionDao {

    @Insert
    suspend fun insertSession(session: SessionEntity)

    @Delete
    suspend fun deleteSession(session: SessionEntity)

    @Query("DELETE FROM sessionentity")
    suspend fun deleteAllSession()

    @Insert
    suspend fun insertExercise(exercise: exercisesEntity)

    @Delete
    suspend fun deleteExercise(exercise: exercisesEntity)

    @Update
    suspend fun updateExercise(exercise: exercisesEntity)

    @Query("SELECT * FROM SessionEntity")
    suspend fun getAllSessions(): List<SessionEntity>?

    @Query("SELECT * FROM exercisesentity WHERE id = :exerciseId")
    suspend fun getExerciseById(exerciseId: Int): exercisesEntity?

    @Transaction
    @Query("SELECT * FROM sessionentity WHERE id = :sessionId")
    suspend fun getSessionWithExercises(sessionId: Int): SessionWithExercises?

    @Entity
    data class SessionWithExercises(
        @Embedded val session: SessionEntity,
        @Relation(
            parentColumn = "id",
            entityColumn = "sessionId"
        )
        val exercises: List<exercisesEntity>
    )
}