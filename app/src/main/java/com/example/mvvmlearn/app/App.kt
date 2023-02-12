package com.example.mvvmlearn.app

import android.content.Context
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication(){
    companion object {
        lateinit var applicationContext: Context

        private fun setAppContext(context: Context) {
            this.applicationContext = context
        }
    }
    override fun onCreate() {
        super.onCreate()
        setAppContext(applicationContext)
    }
}
