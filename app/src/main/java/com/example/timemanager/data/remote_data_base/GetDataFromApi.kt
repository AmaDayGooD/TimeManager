package com.example.timemanager.data.remote_data_base

import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.DataToken
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import retrofit2.Retrofit

class GetDataFromApi(retrofit: Retrofit) {

    private val api = retrofit.create(RemoteApi::class.java)

    suspend fun login(dataLogin: DataProfile): DataToken? {
        println("MyLog $dataLogin")
        return api.login(dataLogin)
    }

    suspend fun registration(body: DataProfile) {
        api.registration(body)
    }

    suspend fun getProfile(token: String): Profile {
        return api.getProfile(token)
    }

    suspend fun editProfile(token: String, profile: DataProfile) {
        api.editProfile(token, profile)
    }

    suspend fun getTasks(token: String): List<Task> {
        return api.getAllTasks(token)
    }
}