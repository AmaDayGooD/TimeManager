package com.example.timemanager.ui.screens.list_task

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject
import com.example.timemanager.ui.screens.list_task.TasksActivity.StateSortField as StateSortField
import com.example.timemanager.ui.screens.list_task.TasksActivity.StateOrder as StateOrder

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

    private val token: String = "$PREFIX_TOKEN ${settings.getToken()}"

    private var currentSortState = 0
    private var currentOrderState = 0

    fun updateList() {
        launch {
            viewState.showLoading()
            val listTasks = repository.getTasks(
                token,
                StateSortField.entries[currentSortState].name,
                StateOrder.entries[currentOrderState].name
            )
            viewState.setTaskList(listTasks ?: emptyList())
        }
    }

    fun changeSortField() {
        currentSortState = (currentSortState + 1) % 5
        println("MyLog $currentSortState ${StateSortField.entries[currentSortState].icon}")
        setListTask()
    }

    fun changeOrder() {
        currentOrderState = (currentOrderState + 1) % 2
        setListTask()
    }

    private fun setListTask() {
        launch {
            val field = StateSortField.entries[currentSortState]
            val order = StateOrder.entries[currentOrderState]

            val listTasks = repository.getTasks(
                token,
                field.name,
                order.name
            )
            viewState.setIconSortField(field)
            viewState.setIconSortOrder(order)
            viewState.setTaskList(listTasks ?: emptyList())

        }
    }
}