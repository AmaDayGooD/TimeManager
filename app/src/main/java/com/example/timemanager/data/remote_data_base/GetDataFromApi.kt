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

    suspend fun getTask(token: String, idTask: String): Task {
        return api.getTask(token, idTask)
    }

    suspend fun updateTask(token: String, task: DataTask){
        return api.updateTask(token, task.idTask.toString(), task)
    }

    suspend fun getChildrenTasks(token: String, childId: String): List<Task> {
        return api.getChildrenTasks(token, childId)
    }

    suspend fun getChildren(token: String): List<Profile> {
        return api.getChildren(token)
    }

    suspend fun getChild(token: String, childId: String): Profile? {
        return api.getChild(token, childId)
    }

    suspend fun addChild(token: String, profile: DataProfile) {
        api.addChild(token, profile)
    }
}