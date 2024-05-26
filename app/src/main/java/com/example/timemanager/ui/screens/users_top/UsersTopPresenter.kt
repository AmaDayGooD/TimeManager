package com.example.timemanager.ui.screens.users_top

import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Created by Alexander Shibaev on 26.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class UsersTopPresenter : BasePresenter<UsersTopView>() {

    init {
        TimeManagerApp.appComponent!!.inject(this)
    }

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var dataBase: DataBaseDao

    @Inject
    lateinit var settings: Settings

    private val repository: Repository = Repository(coroutineContext, retrofit, dataBase)

    private val token: String = "$PREFIX_TOKEN ${settings.getToken()}"

    private var family: Boolean = true
    private var balance: Boolean = false

    init {
        getUsersRating()
    }

    fun requestListUsers(newFamily: Boolean = false, newBalance: Boolean = false) {
        family = newFamily
        balance = newBalance
        getUsersRating()
    }

    private fun getUsersRating() {
        launch {
            val result = repository.getTopUser(token = token, family = family, sortByBalance = balance)
            viewState.setList(result)
        }
    }
}