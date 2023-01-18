package com.example.newsapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        appContext = this
        super.onCreate()
    }
    companion object{
        lateinit var appContext: Application
    }
}