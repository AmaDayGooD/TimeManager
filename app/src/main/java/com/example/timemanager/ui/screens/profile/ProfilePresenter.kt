package com.example.timemanager.ui.screens.profile

import android.util.Log
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.entity.Profile
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class ProfilePresenter : BasePresenter<ProfileView>() {



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

    private lateinit var profile: Profile
    private val token: String

    init {
        viewState.showLoading()
        token = "$PREFIX_TOKEN ${getToken()}"
        getProfile()
    }

    fun getProfile() {
        launch {
            profile = repository.getProfile(token)
            viewState.setProfile(profile)
        }
    }

    private fun getToken(): String {
        return settings.getToken() ?: ""
    }

    fun isParent(): Boolean = profile.userRole == Role.Parent

    fun editProfile(firstName: String, lastName: String) {
        viewState.showLoading()
        firstName.replace('ё', 'е', ignoreCase = true)
        lastName.replace('ё', 'е', ignoreCase = true)
        val profile = DataProfile(
            username = "$firstName $lastName"
        )
        launch {
            repository.editProfile(token, profile)
            viewState.setDefaultVisibleButton()
            getProfile()
        }
    }

    fun requestGotoAuthorization() {
        settings.deleteToken()
        viewState.gotoAuthorization()
    }
}