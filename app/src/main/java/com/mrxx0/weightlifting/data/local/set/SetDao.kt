package com.mrxx0.weightlifting.data.local.set

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSets(sets: SetEntity)

    @Update
    suspend fun updateSets(sets: SetEntity)

    @Delete
    suspend fun deleteSets(sets: SetEntity)

    @Query("SELECT * FROM sets WHERE exerciseId = :exerciseId")
    suspend fun getSetsForExercises(exerciseId: Int): List<SetEntity>
}