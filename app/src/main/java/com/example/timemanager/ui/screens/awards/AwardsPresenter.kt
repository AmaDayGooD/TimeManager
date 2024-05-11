package com.example.timemanager.ui.screens.awards

import com.example.timemanager.R
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataAward
import com.example.timemanager.data.DataError
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.entity.Award
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BasePresenter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.launch
import retrofit2.HttpException
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

    @Inject
    lateinit var moshi: Moshi

    private val adapter = moshi.adapter(DataError::class.java)

    private val repository: Repository = Repository(coroutineContext, retrofit, dataBase)

    private val token: String = "$PREFIX_TOKEN ${settings.getToken()}"

    private var profile: Profile? = null
    private var listAward = listOf<Award>()

    init {
        updateListAward()
    }

    fun createAward(awardName: String, awardDescription: String, awardPrice: String) {
        launch {
            val awardBody = DataAward(
                awardName = awardName, description = awardDescription, price = awardPrice
            )
            viewState.resultCreatingAward(repository.createAward(token, awardBody))
        }
    }

    fun updateListAward() {
        launch {
            profile = repository.getProfile(token)
            viewState.setListAdapter(profile)
            viewState.setRole(profile?.userRole == Role.Parent)
            listAward = repository.getAllAwards(token)
            viewState.setAwardList(listAward)

        }
    }

    fun getAward(awardId: Int) {
        if ((profile?.count ?: 0) < listAward[awardId].priceAward) {
            viewState.showDialogError(AwardErrors.LACK_OFF_FIRE)
            return
        }
        launch {
            try {
                repository.addAwardForUser(token, awardId)
                updateListAward()
            } catch (e: HttpException) {
                val text = e.response()?.errorBody()?.string()
                val errorMessage: DataError? = adapter.fromJson(text)
                errorMessage?.let {
                    if (it.error == "User already has this award") {
                        viewState.showDialogError(AwardErrors.ALREADY_RECEIVED)
                    }
                }

                e.printStackTrace()
            }
        }
    }

    enum class AwardErrors(val stringId: Int) {
        LACK_OFF_FIRE(R.string.lack_off_fire), ALREADY_RECEIVED(R.string.already_received),
    }
}