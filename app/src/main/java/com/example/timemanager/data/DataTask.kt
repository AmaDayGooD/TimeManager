package com.example.timemanager.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timemanager.R
import com.example.timemanager.entity.Task
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.reflect.KCallable
import kotlin.reflect.full.starProjectedType

@RequiresApi(Build.VERSION_CODES.O)
data class DataTask(
    override val idTask: Int?,
    override val relationId: Int?,
    override val taskName: String,
    override val description: String,
    val executionTime: String?,
    val startDateTime: String?,
    val endDateTime: String?,
    override val award: String,
    val status: String?,
    val importance: String?,
    override val error: String?
) : Task {
    constructor(task: Task) : this(
        task.idTask,
        task.relationId,
        task.taskName,
        task.description,
        "",
        "",
        "",
        task.award,
        null,
        null,
        task.error
    )

    override val completedAt: LocalDateTime
        get() = LocalDateTime.ofInstant(Instant.parse(executionTime), ZoneId.of("UTC"))

    override val taskStart: LocalDateTime
        get() = LocalDateTime.ofInstant(Instant.parse(startDateTime), ZoneId.of("UTC"))

    override val taskEnd: LocalDateTime
        get() = LocalDateTime.ofInstant(Instant.parse(endDateTime), ZoneId.of("UTC"))

    override val seriousness: Importance
        get() = Importance.valueOf(importance ?: Importance.Low.toString())

    override val condition: Condition
        get() = Condition.valueOf(status ?: Condition.Accept.toString())
}

enum class Importance {
    Low, Medium, High, ExtraHigh
}

enum class Condition(val colorRes: Int, val textResId: Int) {
    Open(R.color.main, R.string.task_open),
    Completed(R.color.completed, R.string.task_completed),
    Reject(R.color.reject, R.string.task_reject),
    Accept(R.color.accept, R.string.task_accept)
}

