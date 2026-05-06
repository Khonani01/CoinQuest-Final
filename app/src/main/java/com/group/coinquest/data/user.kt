package com.group.coinquest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int = 0,
    val username: String,
    val email: String,
    val passwordHash: String,
    val xp: Int = 0, // For the gamification system
    val currentStreak: Int = 0
)