package com.example.timemanager.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModules(private val context:Context) {
    @Provides
    fun provideContext():Context{
        return context
    }
}