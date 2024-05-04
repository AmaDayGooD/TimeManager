package com.example.timemanager.ui.screens.create_task

import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Created by Alexander Shibaev on 04.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
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

    private val token: String = "${ProfilePresenter.PREFIX_TOKEN} ${settings.getToken()}"

    private val currentDataTask: DataTask? = null

    init {
        getListNameChild()
    }

    private fun getListNameChild() {
        val listNameChildren = mutableListOf<String>()
        launch {
            val listChildren = repository.getChildren(token)
            listChildren?.let { list ->
                list.forEach { child ->
                    child.username?.let { listNameChildren.add(it) }
                }
                viewState.setNameChild(listNameChildren)
            }
        }
    }

}