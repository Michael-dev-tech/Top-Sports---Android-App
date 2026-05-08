package com.example.topsports.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.topsports.data.local.entity.Athlete

@Dao
interface AthleteDao {

    @Query("SELECT * FROM athletes")
    fun getAllAthletes(): LiveData<List<Athlete>>

    @Query("SELECT * FROM athletes WHERE username = :username LIMIT 1")
    suspend fun getAthleteByUsername(username: String): Athlete?

    @Query("SELECT * FROM athletes WHERE sport = :sport")
    fun getAthletesBySport(sport: String): LiveData<List<Athlete>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAthlete(athlete: Athlete)

    @Update
    suspend fun updateAthlete(athlete: Athlete)

    @Delete
    suspend fun deleteAthlete(athlete: Athlete)

    @Query("SELECT COUNT(*) FROM athletes WHERE sport = :sport")
    suspend fun countAthletesBySport(sport: String): Int
}
