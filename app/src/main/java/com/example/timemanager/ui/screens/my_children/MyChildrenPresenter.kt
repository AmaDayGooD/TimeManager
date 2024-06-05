package com.example.timemanager.ui.screens.my_children

import android.provider.ContactsContract.Data
import android.widget.EditText
import com.example.timemanager.TimeManagerApp
import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.Repository
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Settings
import com.example.timemanager.ui.base.BasePresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class MyChildrenPresenter : BasePresenter<MyChildrenView>() {

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


    fun addChild(login: String) {
        val profile = DataProfile(
            login = login
        )
        launch {
            val result = repository.addChild(token, profile)
            if (result) {
                getChild()
                viewState.successfullyAdded()
            } else viewState.filedAdded()
        }
    }

    fun getChild() {
        viewState.showLoading()
        launch {
            val listChildren = repository.getChildren(token)
            viewState.setList(listChildren.orEmpty())
        }
    }


}