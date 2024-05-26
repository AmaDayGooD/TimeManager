package com.example.timemanager.di


import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.ui.screens.authorization.AuthorizationPresenter
import com.example.timemanager.ui.screens.awards.AwardsPresenter
import com.example.timemanager.ui.screens.create_task.CreateTaskPresenter
import com.example.timemanager.ui.screens.list_task.TasksPresenter
import com.example.timemanager.ui.screens.my_awards.MyAwardsPresenter
import com.example.timemanager.ui.screens.my_children.MyChildrenPresenter
import com.example.timemanager.ui.screens.my_task.MyTaskPresenter
import com.example.timemanager.ui.screens.profile.ProfilePresenter
import com.example.timemanager.ui.screens.registration.RegistrationPresenter
import com.example.timemanager.ui.screens.users_top.UsersTopPresenter
import com.squareup.moshi.Moshi
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ContextModules::class, RoomModules::class, NetWorkModules::class, SettingsModule::class])
interface AppComponent {
    fun inject(presenter: AuthorizationPresenter)
    fun inject(presenter: RegistrationPresenter)
    fun inject(presenter: ProfilePresenter)
    fun inject(presenter: TasksPresenter)
    fun inject(presenter: MyChildrenPresenter)
    fun inject(presenter: MyTaskPresenter)
    fun inject(presenter: CreateTaskPresenter)
    fun inject(presenter: AwardsPresenter)
    fun inject(presenter: MyAwardsPresenter)
    fun inject(presenter: UsersTopPresenter)

    val moshi: Moshi
    val room: DataBaseDao
    val retrofit: Retrofit
}