package com.group.coinquest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses_table")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val expenseId: Int = 0,
    val categoryId: Int, // Foreign key linking to Category
    val amount: Double,
    val date: String,
    val startTime: String, // Added to satisfy rubric
    val endTime: String,   // Added to satisfy rubric
    val description: String,
    val receiptPhotoPath: String? // Optional photo path
)