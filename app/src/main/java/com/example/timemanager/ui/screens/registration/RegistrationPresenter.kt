package com.example.timemanager.ui.screens.registration

import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import java.util.TimeZone
import javax.inject.Inject

class RegistrationPresenter() : BasePresenter<RegistrationView>() {

    companion object {
        private const val ALL = "ALL"
        private const val FIRST_NAME = "FIRST_NAME"
        private const val LAST_NAME = "LAST_NAME"
        private const val LOGIN = "LOGIN"
        private const val PASSWORD = "PASSWORD"
    }

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

    private var profile: DataProfile = DataProfile()

    fun onChoiceParentRole(){
        profile = profile.copy(role = "Parent")
        viewState.closeChoiceRole()
    }

    fun onChildRole(){
        profile = profile.copy(role = "Child")
        viewState.closeChoiceRole()
    }

    fun onCheckFirstAndLastNames(firstName: String, lastName: String) {
        firstName.replace('ё','е',ignoreCase = true)
        lastName.replace('ё','е',ignoreCase = true)
        when{
            (firstName.isEmpty() && lastName.isEmpty()) -> {
                viewState.emptyFirstAndLastNames(ALL)
                return
            }
            firstName.isEmpty() -> {
                viewState.emptyFirstAndLastNames(FIRST_NAME)
                return
            }
            lastName.isEmpty() -> {
                viewState.emptyFirstAndLastNames(LAST_NAME)
                return
            }
        }

        profile = profile.copy(
            username = "$firstName $lastName"
        )
        viewState.successfulEnterFirstAndLastName()
    }

    fun onCheckLoginAndPassword(login:String, password:String){
        when{
            (login.isEmpty() && login.isEmpty()) -> {
                viewState.emptyLoginAndPassword(ALL)
                return
            }
            login.isEmpty() -> {
                viewState.emptyLoginAndPassword(LOGIN)
                return
            }
            password.isEmpty() -> {
                viewState.emptyLoginAndPassword(PASSWORD)
                return
            }
        }
        profile = profile.copy(
            login = login, password = password, timezone = TimeZone.getDefault().id
        )
        registration(profile)
    }

    private fun registration(profile: DataProfile){
        launch {
            val result = repository.registration(profile)
            if (result == "successful") {
                successfulRegistration(profile)
            } else {
                viewState.failedRegistration(result)
            }
        }
    }

    private fun successfulRegistration(profile: DataProfile) {
        launch {
            val token = repository.login(profile.login!!, profile.password!!)
            settings.saveToken(token.orEmpty())
            viewState.successfulRegistration()
        }
    }
}