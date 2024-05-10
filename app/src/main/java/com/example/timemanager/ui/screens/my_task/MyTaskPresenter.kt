package com.example.timemanager.ui.screens.my_task

import com.example.timemanager.R
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Condition
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.Importance
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BasePresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import com.omega_r.libs.extensions.common.ifNull
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

    private val token: String = "$PREFIX_TOKEN ${settings.getToken()}"

    private var taskInfo: Task? = null
    private var profile: Profile? = null
    private var childUserId: Int? = null

    init {
        setTask()
    }

    private fun setTask() {
        launch {
            viewState.showLoading()
            taskInfo = repository.getTask(token, taskId.toString())
            val taskPerformer: Profile? = repository.getChildByRelationId(token, taskInfo?.relationId ?: 1)
            childUserId = taskPerformer?.id
            profile = repository.getProfile(token)
            val userRole = profile?.userRole ?: Role.Child
            viewState.setTaskInfo(taskInfo, userRole, taskPerformer)
        }
    }

    fun getTask(): Task {
        return taskInfo!!
    }

    fun applyChanges(newTask: DataTask) {
        launch {
            updateTask(newTask)
        }
    }

    fun taskCompleted() {
        applyChanges((taskInfo as DataTask).copy(status = Condition.Completed.toString()))
        viewState.taskCompletedShowDialog()
    }

    fun payReward(reward: Float) {
        launch {
            repository.payReward(token, childUserId.toString(), reward)
        }
    }

    private fun updateTask(newTask: DataTask) {
        launch {
            viewState.showLoading()
            repository.updateTask(token, newTask)
            viewState.closeLoading()
            setTask()
            viewState.closeDialogChangeStatus()
        }
    }


}