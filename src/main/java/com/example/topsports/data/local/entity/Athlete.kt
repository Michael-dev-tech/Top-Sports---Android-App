package com.example.topsports.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "athletes")
data class Athlete(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "full_name")
    val fullName: String,

    @ColumnInfo(name = "age")
    val age: Int,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "sport")
    val sport: String,

    @ColumnInfo(name = "years_experience")
    val yearsExperience: Int,

    @ColumnInfo(name = "bio")
    val bio: String = ""
)
