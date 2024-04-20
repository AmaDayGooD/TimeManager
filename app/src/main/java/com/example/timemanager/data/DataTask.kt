package com.example.timemanager.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timemanager.entity.Task
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

data class DataTask(
    override val idTask: Int,
    override val parentUserId: String,
    override val childUserId: String,
    override val taskName: String,
    override val description: String,
    val deadline: String,
    override val award: String,
    override val status: String,
    val importance : String?,
) : Task {
    constructor(task: Task) : this(
        task.idTask,
        task.parentUserId,
        task.childUserId,
        task.taskName,
        task.description,
        "",
        task.award,
        task.status,
        null
    )

    override val limit: LocalDateTime
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return LocalDateTime.ofInstant(Instant.parse(deadline), ZoneId.of("UTC"))
        }

    override val seriousness: Importance
        get() {
            println("MyLog $importance")
            return Importance.valueOf(importance ?: Importance.Low.toString())
        }
}

enum class Importance {
    Low, Medium, High, ExtraHigh
}