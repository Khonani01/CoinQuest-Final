package com.group.coinquest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Version is 1 because this is our first time building it
@Database(entities = [User::class, Category::class, Expense::class], version = 1, exportSchema = false)
abstract class CoinQuestDatabase : RoomDatabase() {

    abstract fun coinQuestDao(): CoinQuestDao

    companion object {
        @Volatile
        private var INSTANCE: CoinQuestDatabase? = null

        fun getDatabase(context: Context): CoinQuestDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinQuestDatabase::class.java,
                    "coinquest_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}