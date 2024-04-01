package com.example.timemanager.data.local_data_base

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DataBaseDao {
    @Query("SELECT * FROM Users")
    suspend fun getAllUsers():List<LocalUsers>
}