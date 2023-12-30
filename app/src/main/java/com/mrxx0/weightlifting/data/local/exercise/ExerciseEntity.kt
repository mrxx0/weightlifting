package com.mrxx0.weightlifting.data.local.exercise

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mrxx0.weightlifting.data.local.session.SessionEntity
import com.mrxx0.weightlifting.data.local.set.SetEntity

@Entity(
    tableName = "exercise", foreignKeys = [ForeignKey(
        entity = SessionEntity::class,
        parentColumns = ["id"],
        childColumns = ["sessionId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = null,
    var sets: MutableList<SetEntity>? = null,
    val sessionId: Int // Foreign key linking to SessionEntity
)