package com.example.timemanager.ui.screens.create_task

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Condition
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.Importance
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.time.LocalDateTime
import javax.inject.Inject


/**
 * Created by Alexander Shibaev on 04.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
@RequiresApi(Build.VERSION_CODES.O)
class CreateTaskPresenter : BasePresenter<CreateTaskView>() {
    init {
        TimeManagerApp.appComponent!!.inject(this)
    }

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var dataBase: DataBaseDao

    @Inject
    lateinit var settings: Settings

    private var repository: Repository = Repository(coroutineContext, retrofit, dataBase)

    private val token: String = "$PREFIX_TOKEN ${settings.getToken()}"

    private var currentDataTask: DataTask
    private var listChildren: MutableList<Profile> = mutableListOf()
    private var childId: String = ""

    init {
        getListNameChild()
        currentDataTask = DataTask(
            idTask = null,
            relationId = null,
            taskName = "",
            description = "",
            executionTime = "",
            startDateTime = null,
            endDateTime = null,
            award = "",
            status = null,
            importance = null,
            error = null
        )
    }

    fun setChildId(userName: String) {
        childId = (listChildren.find { it.username == userName }?.id ?: 0).toString()
    }

    fun createNewTask(
        taskName: String,
        taskDescription: String,
        seriousness: Importance,
        award: String,
        dateTimeStart: LocalDateTime?,
        dateTimeEnd: LocalDateTime?,
    ) {
        viewState.showLoading()
        currentDataTask = currentDataTask.copy(
            taskName = taskName.trim(),
            description = taskDescription.trim(),
            startDateTime = dateTimeStart.toString(),
            endDateTime = dateTimeEnd.toString(),
            status = Condition.Open.toString(),
            award = award,
            importance = seriousness.name
        )
        launch {
            viewState.showResultCreateTask(repository.createTask(token, childId, currentDataTask))


        }
    }

    private fun getListNameChild() {
        var listNameChildren = mutableListOf<String>()
        launch {
            listChildren.addAll(repository.getChildren(token) ?: emptyList())
            listChildren.let { list ->
                list.forEach { child ->
                    child.username?.let { listNameChildren.add(it) }
                }
                viewState.setNameChild(listNameChildren)
            }
        }
    }

}