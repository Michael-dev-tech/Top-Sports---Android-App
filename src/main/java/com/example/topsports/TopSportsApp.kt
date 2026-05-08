package com.example.topsports

import android.app.Application
import com.example.topsports.data.local.AppDatabase

class TopSportsApp : Application() {

    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }

    companion object {
        lateinit var instance: TopSportsApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
