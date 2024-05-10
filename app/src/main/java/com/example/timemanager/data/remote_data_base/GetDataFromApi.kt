package com.example.timemanager.data.remote_data_base

import com.example.timemanager.data.DataAward
import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.DataToken
import com.example.timemanager.entity.Award
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import retrofit2.Retrofit
import java.util.TimeZone

class GetDataFromApi(retrofit: Retrofit) {

    private val api = retrofit.create(RemoteApi::class.java)

    suspend fun login(dataLogin: DataProfile): DataToken? {
        return try {
            val token = api.login(dataLogin, TimeZone.getDefault().id)
            println("MyLog NOT ERROR $token")
            token
        }catch (e:Exception){
            e.printStackTrace()
            println("MyLog ERROR $e")
            null
        }
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

    suspend fun payReward(token: String, userId: String, reward: Float) {
        api.payReward(token, userId, reward)
    }

    suspend fun createTask(token: String, childId: String, newTask: DataTask) {
        api.createTask(token, childId, newTask)
    }

    suspend fun getTasks(token: String, sortField: String, sortOrder: String): List<Task> {
        return api.getAllTasks(token, sortField, sortOrder)
    }

    suspend fun getTask(token: String, idTask: String): Task {
        return api.getTask(token, idTask)
    }

    suspend fun updateTask(token: String, task: DataTask) {
        return api.updateTask(token, task.idTask.toString(), task)
    }

    suspend fun getChildrenTasks(token: String, childId: String): List<Task> {
        return api.getChildrenTasks(token, childId)
    }

    suspend fun getChildren(token: String): List<Profile> {
        return api.getChildren(token)
    }

    suspend fun getChild(token: String, childId: Int): Profile? {
        return api.getChild(token, childId)
    }

    suspend fun getChildByRelationId(token: String, relationId: Int): DataProfile? {
        return api.getChildByRelationId(token, relationId)
    }

    suspend fun addChild(token: String, profile: DataProfile) {
        api.addChild(token, profile)
    }

    suspend fun createAward(token: String, award: DataAward) {
        api.createAward(token, award)
    }

    suspend fun addAwardForUser(token: String, userId: Int, awardId: Int) {
        api.addAwardForUser(token, userId, awardId)
    }

    suspend fun getAllAwards(token: String): List<Award> {
        return api.getAllAwards(token)
    }

    suspend fun getAwardsByUser(token: String, userId: String): List<Award> {
        return api.getAwardsByUser(token, userId)
    }
}