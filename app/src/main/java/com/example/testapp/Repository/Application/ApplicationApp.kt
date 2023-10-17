package com.example.testapp.ViewModel

import android.app.Application

class ApplicationApp:Application() {
    override fun onCreate() {
        super.onCreate()
        App.init(this)
    }
}