package com.example.topsports.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.topsports.TopSportsApp
import com.example.topsports.databinding.ActivityLoginBinding
import com.example.topsports.ui.main.MainActivity
import com.example.topsports.utils.SessionManager
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        // Dacă e deja logat, mergi direct la MainActivity
        if (sessionManager.isLoggedIn()) {
            goToMain()
            return
        }

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInputs(username, password)) {
                loginUser(username, password)
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateInputs(username: String, password: String): Boolean {
        if (username.isEmpty()) {
            binding.etUsername.error = "Introduceți username-ul"
            return false
        }
        if (password.isEmpty()) {
            binding.etPassword.error = "Introduceți parola"
            return false
        }
        if (password.length < 6) {
            binding.etPassword.error = "Parola trebuie să aibă minim 6 caractere"
            return false
        }
        return true
    }

    private fun loginUser(username: String, password: String) {
        lifecycleScope.launch {
            val db = (application as TopSportsApp).database
            val athlete = db.athleteDao().getAthleteByUsername(username)

            if (athlete != null) {
                // Salvăm sesiunea în SharedPreferences
                sessionManager.saveLoginSession(username)
                goToMain()
            } else {
                Toast.makeText(
                    this@LoginActivity,
                    "Username sau parolă incorectă",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
