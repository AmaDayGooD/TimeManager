package com.example.timemanager.ui.screens.list_task

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.entity.Task
import com.example.timemanager.ui.base.BasePresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class TasksPresenter : BasePresenter<TasksView>() {
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

    private var showAllTasks: Boolean = true


    fun updateList(){
        launch {
            viewState.showLoading()
            val listTasks = repository.getTasks(token)
            showAllTasks = true
            viewState.setTaskList(listTasks ?: emptyList(), showAllTasks)
        }
    }

    fun changeViewList() {
        showAllTasks = !showAllTasks
        launch {
            val listTasks = repository.getTasks(token)
            if (showAllTasks) {
                viewState.setTaskList(listTasks ?: emptyList(), showAllTasks)
            } else {
                viewState.setTaskList(getToDayTasks(listTasks) ?: emptyList(), showAllTasks)
            }
        }
    }

    private fun getToDayTasks(listTasks: List<Task>?): List<Task>? {
        val toDay = LocalDate.now()
        return listTasks?.filter { it.limit.toLocalDate() == toDay }
    }
}