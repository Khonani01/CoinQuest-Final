package com.group.coinquest

import java.text.SimpleDateFormat
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.group.coinquest.data.CoinQuestDatabase
import com.group.coinquest.data.Expense
import com.group.coinquest.databinding.ActivityAddExpenseBinding
import kotlinx.coroutines.launch
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddExpenseBinding
    private var startTimeStr = "09:00"
    private var endTimeStr = "10:00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Placeholder categories
        val categories = arrayOf("Groceries", "Transport", "Entertainment", "Work")
        binding.spCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)

        binding.btnCapturePhoto.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 101)
        }

        binding.btnSaveExpense.setOnClickListener {
            saveToDatabase()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.ivReceiptPreview.setImageBitmap(imageBitmap)
            binding.ivReceiptPreview.visibility = View.VISIBLE
        }
    }

    private fun saveToDatabase() {
        val amount = binding.etAmount.text.toString().toDoubleOrNull() ?: 0.0
        val desc = binding.etDescription.text.toString()

        if (amount <= 0 || desc.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        lifecycleScope.launch {
            val db = CoinQuestDatabase.getDatabase(this@AddExpenseActivity)
            val newExpense = Expense(
                categoryId = 1, // Default for prototype
                amount = amount,
                date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()),
                startTime = startTimeStr,
                endTime = endTimeStr,
                description = desc,
                receiptPhotoPath = null // Simplified for prototype
            )
            db.coinQuestDao().insertExpense(newExpense)
            Toast.makeText(this@AddExpenseActivity, "Expense Tracked!", Toast.LENGTH_SHORT).show()
            finish() // Return to Dashboard
        }
    }
}