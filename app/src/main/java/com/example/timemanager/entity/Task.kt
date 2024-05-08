package com.example.timemanager.entity

import com.example.timemanager.data.Condition
import com.example.timemanager.data.Importance
import java.time.LocalDateTime

interface Task {
    val idTask: Int?
    val relationId: Int?
    val taskName: String
    val description: String
    val taskStart: LocalDateTime
    val taskEnd: LocalDateTime
    val award: String
    val condition: Condition?
    val seriousness: Importance?
    val error: String?
}