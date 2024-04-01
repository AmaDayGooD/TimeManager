package com.example.timemanager.di

import android.content.Context
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.TasksDB
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModules::class])
class RoomModules {
    @Provides
    fun provideDataBase(context:Context):DataBaseDao{
        return TasksDB.createDataBase(context).dao
    }
}