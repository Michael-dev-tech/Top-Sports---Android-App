package com.example.topsports.data.remote.api

import com.example.topsports.data.remote.model.AllSportsResponse
import com.example.topsports.data.remote.model.LeaguesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SportsApiService {

    // API 1: Toate sporturile
    @GET("api/v1/json/3/all_sports.php")
    suspend fun getAllSports(): Response<AllSportsResponse>

    // API 2: Liga pentru un sport specific
    @GET("api/v1/json/3/search_all_leagues.php")
    suspend fun getLeaguesBySport(
        @Query("s") sport: String
    ): Response<LeaguesResponse>
}
