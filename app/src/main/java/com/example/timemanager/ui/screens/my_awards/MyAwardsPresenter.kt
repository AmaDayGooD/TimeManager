package com.example.timemanager.ui.screens.my_awards

import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Created by Alexander Shibaev on 11.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class MyAwardsPresenter : BasePresenter<MyAwardsView>() {

    init {
        TimeManagerApp.appComponent!!.inject(this)
    }

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var dataBase: DataBaseDao

    @Inject
    lateinit var settings: Settings

    private val token: String = "$PREFIX_TOKEN ${settings.getToken()}"

    private val repository: Repository = Repository(coroutineContext, retrofit, dataBase)

    init {
        viewState.setListAdapter()
        getMyAwards()
    }

    private fun getMyAwards() {
        launch {
            viewState.setList(repository.getMyAwards(token))
        }
    }

}