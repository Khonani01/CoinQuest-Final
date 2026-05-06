package com.group.coinquest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories_table")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Int = 0,
    val name: String,
    val monthlyMinGoal: Double = 0.0, // Added to satisfy rubric
    val monthlyMaxGoal: Double        // Added to satisfy rubric
)