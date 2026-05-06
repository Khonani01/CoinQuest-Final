package com.group.coinquest

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.group.coinquest.databinding.ActivityBudgetSettingsBinding

class BudgetSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBudgetSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // SeekBar Logic for Max Goal [cite: 5]
        binding.sbMaxGoal.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvMaxLabel.text = "Maximum Monthly Goal: R$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // SeekBar Logic for Min Goal [cite: 19]
        binding.sbMinGoal.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvMinLabel.text = "Minimum Monthly Goal: R$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.btnSaveGoals.setOnClickListener {
            Toast.makeText(this, "Monthly Goals Updated!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}