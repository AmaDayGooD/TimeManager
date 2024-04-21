package com.example.timemanager.ui.screens.my_task

import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BasePresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class MyTaskPresenter(private val taskId: Int) : BasePresenter<MyTaskView>() {
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

    private val token: String = "${ProfilePresenter.PREFIX_TOKEN} ${settings.getToken()}"

    private var taskInfo: Task? = null
    private var userRole: Role = Role.Child

    init {
        launch {
            taskInfo = repository.getTask(token, taskId.toString())
            userRole = repository.getProfile(token).userRole ?: Role.Child
            viewState.setTaskInfo(taskInfo, userRole)
        }
    }

}