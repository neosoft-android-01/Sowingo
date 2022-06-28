package com.neosofttech.sowingo.global

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SowingoApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}