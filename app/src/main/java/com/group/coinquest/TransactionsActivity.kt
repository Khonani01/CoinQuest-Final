package com.group.coinquest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.group.coinquest.data.CoinQuestDatabase
import com.group.coinquest.databinding.ActivityTransactionsBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TransactionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.rvTransactions.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val db = CoinQuestDatabase.getDatabase(this@TransactionsActivity)
            db.coinQuestDao().getAllExpenses().collect { list ->
                binding.rvTransactions.adapter = TransactionAdapter(list)
            }
        }
    }
}