package com.group.coinquest

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.group.coinquest.data.CoinQuestDatabase
import com.group.coinquest.data.User
import com.group.coinquest.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

/**
 * Controller for the Login/Register screen using MVC pattern[cite: 183].
 * Handles user authentication and navigation to the Quest Dashboard[cite: 195, 312].
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isLoginMode = true // Tracks if user is in Login or Register mode [cite: 196]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide() // Immersive "Game" feel [cite: 87]

        val db = CoinQuestDatabase.getDatabase(this)

        // Toggle between Login and Register forms [cite: 196]
        binding.tvRegisterToggle.setOnClickListener {
            isLoginMode = !isLoginMode
            if (isLoginMode) {
                binding.btnLogin.text = "LOGIN"
                binding.tvRegisterToggle.text = "Don't have an account? Register"
            } else {
                binding.btnLogin.text = "REGISTER"
                binding.tvRegisterToggle.text = "Already have an account? Login"
            }
        }

        binding.btnLogin.setOnClickListener {
            val input = binding.etEmailUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Input validation: Reject empty fields [cite: 129]
            if (input.isEmpty() || password.isEmpty()) {
                binding.tvErrorMessage.text = "Fields cannot be empty"
                binding.tvErrorMessage.visibility = View.VISIBLE
                return@setOnClickListener
            }

            lifecycleScope.launch {
                if (isLoginMode) {
                    // LOGIN LOGIC [cite: 20, 195]
                    val user = db.coinQuestDao().getUserByEmail(input)
                    if (user != null && user.passwordHash == password) {
                        Toast.makeText(this@MainActivity, "Welcome back, ${user.username}!", Toast.LENGTH_SHORT).show()

                        // APPLY INTENT TO NAVIGATE TO DASHBOARD [cite: 14, 313]
                        val intent = Intent(this@MainActivity, DashboardActivity::class.java)
                        startActivity(intent)
                        finish() // Prevent user from returning to login [cite: 129]
                    } else {
                        binding.tvErrorMessage.text = "Invalid credentials"
                        binding.tvErrorMessage.visibility = View.VISIBLE
                    }
                } else {
                    // REGISTER LOGIC [cite: 129, 195]
                    val newUser = User(username = input.split("@")[0], email = input, passwordHash = password)
                    db.coinQuestDao().insertUser(newUser)
                    Toast.makeText(this@MainActivity, "Account Created! Please Login.", Toast.LENGTH_SHORT).show()

                    // Automatically switch back to login mode [cite: 196]
                    isLoginMode = true
                    binding.btnLogin.text = "LOGIN"
                    binding.tvRegisterToggle.text = "Don't have an account? Register"
                }
            }
        }
    }
}