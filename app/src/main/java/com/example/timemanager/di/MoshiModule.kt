package com.example.timemanager.di

import com.example.timemanager.data.DataError
import com.example.timemanager.moshi.ErrorJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
@Module
class MoshiModule {
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(DataError::class.java, ErrorJsonAdapter())
            .build()
    }
}