package com.example.timemanager.data.local_data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalUsers::class], version = 1)
abstract class TasksDB : RoomDatabase() {
    abstract val dao: DataBaseDao
    companion object{
        fun createDataBase(context: Context): TasksDB {
            return Room.databaseBuilder(context, TasksDB::class.java,"tasks.db").build()
        }
    }
}