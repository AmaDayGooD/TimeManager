package com.example.timemanager.ui.screens.authorization

import android.util.Log
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class AuthorizationPresenter() : BasePresenter<AuthorizationView>() {

    init {
        TimeManagerApp.appComponent!!.inject(this)
        if (!settings.getToken().isNullOrEmpty()) {
            viewState.requestGotoMainActivity()
        }
    }

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    lateinit var dataBase: DataBaseDao

    @Inject
    lateinit var settings: Settings

    private val repository: Repository = Repository(coroutineContext, retrofit, dataBase)

    fun checkEmptyTextField(login: String, password: String) {
        if (login.isEmpty() || password.isEmpty()) {
            log("Веди логин и пароль!")
        } else {
            authorization(login, password)
        }
    }

    private fun authorization(login: String, password: String) {
        launch {
            try {
                val token = repository.login(login, password)
                if (token != null) {
                    settings.saveToken(token)
                    viewState.requestGotoMainActivity()
                }
            } catch (e: Exception) {
                log("Error $e")
                e.printStackTrace()
            }
        }
    }

    override fun log(text: String) {
        Log.d("MyLog", text)
    }
}