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
    val status: String?,
    val importance: String?,
) : Task {
    constructor(task: Task) : this(
        task.idTask,
        task.parentUserId,
        task.childUserId,
        task.taskName,
        task.description,
        "",
        task.award,
        null,
        null
    )

    override val limit: LocalDateTime
        @RequiresApi(Build.VERSION_CODES.O)
        get() = LocalDateTime.ofInstant(Instant.parse(deadline), ZoneId.of("UTC"))

    override val seriousness: Importance
        get() = Importance.valueOf(importance ?: Importance.Low.toString())

    override val condition: Condition
        get() = Condition.valueOf(status ?: Condition.Open.toString())
}

enum class Importance {
    Low, Medium, High, ExtraHigh
}

enum class Condition {
    Open, Reject, Accept
}