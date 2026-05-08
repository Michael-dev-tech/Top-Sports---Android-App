package com.example.topsports.data.repository

import androidx.lifecycle.LiveData
import com.example.topsports.data.local.dao.AthleteDao
import com.example.topsports.data.local.entity.Athlete
import com.example.topsports.data.remote.RetrofitClient
import com.example.topsports.data.remote.model.LeagueItem
import com.example.topsports.data.remote.model.SportItem

class SportsRepository(private val athleteDao: AthleteDao) {

    // ---- LOCAL DB ----

    val allAthletes: LiveData<List<Athlete>> = athleteDao.getAllAthletes()

    suspend fun getAthleteByUsername(username: String): Athlete? {
        return athleteDao.getAthleteByUsername(username)
    }

    suspend fun insertAthlete(athlete: Athlete) {
        athleteDao.insertAthlete(athlete)
    }

    suspend fun updateAthlete(athlete: Athlete) {
        athleteDao.updateAthlete(athlete)
    }

    suspend fun deleteAthlete(athlete: Athlete) {
        athleteDao.deleteAthlete(athlete)
    }

    // ---- REMOTE API ----

    suspend fun getAllSports(): List<SportItem> {
        return try {
            val response = RetrofitClient.sportsApiService.getAllSports()
            if (response.isSuccessful) {
                response.body()?.sports ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getLeaguesBySport(sport: String): List<LeagueItem> {
        return try {
            val response = RetrofitClient.sportsApiService.getLeaguesBySport(sport)
            if (response.isSuccessful) {
                response.body()?.leagues ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
