package com.example.timemanager.di


import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.ui.screens.authorization.AuthorizationPresenter
import com.example.timemanager.ui.screens.list_task.TasksPresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import com.example.timemanager.ui.screens.registration.RegistrationPresenter
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ContextModules::class, RoomModules::class, NetWorkModules::class, SettingsModule::class])
interface AppComponent {
    fun inject(presenter: AuthorizationPresenter)
    fun inject(presenter: RegistrationPresenter)
    fun inject(presenter: ProfilePresenter)
    fun inject(presenter: TasksPresenter)

    val room: DataBaseDao
    val retrofit: Retrofit
}