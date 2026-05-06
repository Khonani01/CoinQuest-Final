package com.group.coinquest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.coinquest.data.Expense
import com.group.coinquest.databinding.ItemTransactionBinding

class TransactionAdapter(private val expenses: List<Expense>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expense = expenses[position]
        holder.binding.tvTransactionDesc.text = expense.description
        holder.binding.tvTransactionDate.text = expense.date
        holder.binding.tvTransactionAmount.text = "-R${expense.amount}"
    }
    override fun getItemCount() = expenses.size
}