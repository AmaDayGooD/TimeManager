package com.example.timemanager.data.local_data_base

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class LocalUsers(
    @PrimaryKey(autoGenerate = true)
    val id:Int? = null,
    val role:Role,
    val username :String
)

enum class Role {
    Parent, Child
}
