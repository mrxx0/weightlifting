package com.mrxx0.weightlifting.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SessionEntity)

    @Query("DELETE FROM SessionEntity WHERE id = :sessionId")
    suspend fun deleteSession(sessionId: Int)

    @Query("DELETE FROM SessionEntity")
    suspend fun deleteAllSession()

    @Query("SELECT * FROM ExercisesEntity WHERE sessionId = :sessionId")
    fun getExercisesForSession(sessionId: Int): Flow<List<ExercisesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercises(exercises: List<ExercisesEntity>)

    @Delete
    suspend fun deleteExercise(exercise: ExercisesEntity)

    @Update
    suspend fun updateExercise(exercise: ExercisesEntity)

    @Query("SELECT * FROM SessionEntity")
    fun getAllSessions(): Flow<List<SessionEntity>>

    @Query("SELECT * FROM sessionentity WHERE id= :sessionId")
    suspend fun getSessionById(sessionId: Int): SessionEntity?

    @Query("SELECT * FROM exercisesentity WHERE id = :exerciseId")
    suspend fun getExerciseById(exerciseId: Int): ExercisesEntity?

    @Query("SELECT id FROM SessionEntity")
    fun getAllSessionIds(): List<Int>

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
        val exercises: List<ExercisesEntity>
    )
}