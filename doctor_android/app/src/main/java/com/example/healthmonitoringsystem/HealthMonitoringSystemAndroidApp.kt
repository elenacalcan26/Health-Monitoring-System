package com.example.healthmonitoringsystem

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class HealthMonitoringSystemAndroidApp: Application() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    }

    companion object {
        lateinit var instance: HealthMonitoringSystemAndroidApp
            private set
    }

    init {
        instance = this
    }
} 