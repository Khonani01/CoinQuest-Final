package com.group.coinquest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.group.coinquest.data.CoinQuestDatabase
import com.group.coinquest.databinding.ActivityDashboardBinding
import kotlinx.coroutines.launch

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Logic to transition to Add Expense screen
        binding.fabAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
            // We will build this Activity next!
            // startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        loadDashboardStats()
    }

    private fun loadDashboardStats() {
        val db = CoinQuestDatabase.getDatabase(this)
        lifecycleScope.launch {
            // In a full app, we would calculate this from the database
            // For the prototype display, we use placeholders until categories are added
            binding.tvSafeToSpend.text = "Safe to Spend Today: R850.00"
            binding.tvXP.text = "Level 5\n4500 XP"
            binding.tvBadge.text = "Frugal Hero\nStreak: 4 Days"
        }
    }
}