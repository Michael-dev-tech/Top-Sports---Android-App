package com.example.topsports.data.remote.model

import com.google.gson.annotations.SerializedName

// Response for all sports list
data class AllSportsResponse(
    @SerializedName("sports")
    val sports: List<SportItem>?
)

data class SportItem(
    @SerializedName("idSport")
    val idSport: String,

    @SerializedName("strSport")
    val strSport: String,

    @SerializedName("strSportThumb")
    val strSportThumb: String?,

    @SerializedName("strSportDescription")
    val strSportDescription: String?,

    @SerializedName("strSportIconGreen")
    val strSportIconGreen: String?
)

// Response for leagues
data class LeaguesResponse(
    @SerializedName("leagues")
    val leagues: List<LeagueItem>?
)

data class LeagueItem(
    @SerializedName("idLeague")
    val idLeague: String,

    @SerializedName("strLeague")
    val strLeague: String,

    @SerializedName("strSport")
    val strSport: String,

    @SerializedName("strCountry")
    val strCountry: String?,

    @SerializedName("strLeagueBadge")
    val strLeagueBadge: String?
)
