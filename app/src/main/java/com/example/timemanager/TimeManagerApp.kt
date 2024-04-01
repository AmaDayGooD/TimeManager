package com.example.timemanager

import android.app.Application
import com.example.timemanager.di.AppComponent
import com.example.timemanager.di.ContextModules
import com.example.timemanager.di.DaggerAppComponent

class TimeManagerApp:Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModules(ContextModules(this))
            .build()
    }

    companion object {
        var appComponent: AppComponent? = null
            private set
    }
}