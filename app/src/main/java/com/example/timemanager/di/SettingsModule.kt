package com.example.timemanager.di

import android.content.Context
import com.example.timemanager.data.local_data_base.Settings
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModules::class])
class SettingsModule {
    @Provides
    fun provideSettings(context: Context):Settings{
        return Settings(context)
    }
}