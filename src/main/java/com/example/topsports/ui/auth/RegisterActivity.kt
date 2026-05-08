package com.example.topsports.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.topsports.TopSportsApp
import com.example.topsports.data.local.entity.Athlete
import com.example.topsports.databinding.ActivityRegisterBinding
import com.example.topsports.utils.SessionManager
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val fullName = binding.etFullName.text.toString().trim()
            val age = binding.etAge.text.toString().trim()
            val city = binding.etCity.text.toString().trim()
            val sport = binding.etSport.text.toString().trim()
            val years = binding.etYears.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInputs(username, fullName, age, city, sport, years, password)) {
                registerUser(username, fullName, age.toInt(), city, sport, years.toInt())
            }
        }

        binding.tvLogin.setOnClickListener {
            finish()
        }
    }

    private fun validateInputs(
        username: String, fullName: String, age: String,
        city: String, sport: String, years: String, password: String
    ): Boolean {
        if (username.isEmpty()) { binding.etUsername.error = "Câmp obligatoriu"; return false }
        if (fullName.isEmpty()) { binding.etFullName.error = "Câmp obligatoriu"; return false }
        if (age.isEmpty()) { binding.etAge.error = "Câmp obligatoriu"; return false }
        if (city.isEmpty()) { binding.etCity.error = "Câmp obligatoriu"; return false }
        if (sport.isEmpty()) { binding.etSport.error = "Câmp obligatoriu"; return false }
        if (years.isEmpty()) { binding.etYears.error = "Câmp obligatoriu"; return false }
        if (password.isEmpty()) { binding.etPassword.error = "Câmp obligatoriu"; return false }
        if (password.length < 6) {
            binding.etPassword.error = "Minim 6 caractere"
            return false
        }
        return true
    }

    private fun registerUser(
        username: String, fullName: String, age: Int,
        city: String, sport: String, years: Int
    ) {
        lifecycleScope.launch {
            val db = (application as TopSportsApp).database

            // Verificăm dacă username-ul există deja
            val existing = db.athleteDao().getAthleteByUsername(username)
            if (existing != null) {
                Toast.makeText(
                    this@RegisterActivity,
                    "Username-ul există deja!",
                    Toast.LENGTH_SHORT
                ).show()
                return@launch
            }

            val newAthlete = Athlete(
                username = username,
                fullName = fullName,
                age = age,
                city = city,
                sport = sport,
                yearsExperience = years
            )

            db.athleteDao().insertAthlete(newAthlete)
            sessionManager.saveLoginSession(username)
            sessionManager.saveFavoriteSport(sport)

            Toast.makeText(
                this@RegisterActivity,
                "Cont creat cu succes!",
                Toast.LENGTH_SHORT
            ).show()

            startActivity(Intent(this@RegisterActivity, com.example.topsports.ui.main.MainActivity::class.java))
            finishAffinity()
        }
    }
}
