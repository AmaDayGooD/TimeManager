package com.example.timemanager.ui.screens.awards

import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataAward
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject


/**
 * Created by Alexander Shibaev on 09.05.2024.
 * Copyright (c) 2024 Omega https://omega-r.com
 */
class AwardsPresenter() : BasePresenter<AwardsView>() {

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

    init {
        launch {
            val userBalance = repository.getProfile(token).count ?: 0
            viewState.setListAdapter(userBalance)
            updateListAward()
        }
    }

    fun createAward(awardName: String, awardDescription: String, awardPrice: String) {
        launch {
            val awardBody = DataAward(
                awardName = awardName,
                description = awardDescription,
                price = awardPrice
            )
            viewState.resultCreatingAward(repository.createAward(token, awardBody))
        }
    }

    fun updateListAward() {
        launch {
            val allAwards = repository.getAllAwards(token)
            viewState.setAwardList(allAwards)
        }
    }

}