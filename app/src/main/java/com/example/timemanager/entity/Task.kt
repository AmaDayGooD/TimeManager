package com.example.timemanager.entity

import com.example.timemanager.data.Importance
import java.time.LocalDateTime

interface Task {
    val idTask: Int
    val parentUserId: String
    val childUserId: String
    val taskName: String
    val description: String
    val limit: LocalDateTime
    val award: String
    val status: String
    val seriousness: Importance?
}