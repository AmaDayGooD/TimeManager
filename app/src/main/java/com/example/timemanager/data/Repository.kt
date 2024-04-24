package com.example.timemanager.data

import android.provider.ContactsContract.Data
import com.example.timemanager.data.local_data_base.DataBaseDao
import com.example.timemanager.data.local_data_base.Role
import com.example.timemanager.data.remote_data_base.GetDataFromApi
import com.example.timemanager.entity.Profile
import com.example.timemanager.entity.Task
import com.omega_r.base.logs.log
import kotlinx.coroutines.CoroutineScope
import retrofit2.Retrofit
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class Repository @Inject constructor(
    override val coroutineContext: CoroutineContext,
    retrofit: Retrofit,
    private val dataBaseDao: DataBaseDao,
) : CoroutineScope {

    private val retrofit = GetDataFromApi(retrofit)

    suspend fun login(login: String, password: String): String? {
        val dataLoginPassword = DataProfile(
            login = login,
            password = password
        )
        return try {
            retrofit.login(dataLoginPassword)?.token
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    suspend fun registration(profile: DataProfile): String {
        var dataProfile = profile
        return try {
            if (dataProfile.role == Role.Child.toString()) {
                dataProfile = dataProfile.copy(balance = "18")
            }
            retrofit.registration(dataProfile)
            "successful"
        } catch (e: Exception) {
            "${e.message}"
        }
    }

    suspend fun getProfile(token: String): Profile {
        return retrofit.getProfile(token)
    }

    suspend fun editProfile(token: String, profile: DataProfile) {
        try {
            retrofit.editProfile(token, profile)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun payReward(token: String, userId: String, reward: Float) {
        try {
            retrofit.payReward(token, userId, reward)
        } catch (e:Exception){
            e.printStackTrace()
        }

    }

    suspend fun getTasks(token: String): List<Task>? {
        return try {
            retrofit.getTasks(token)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getTask(token: String, taskId: String): Task? {
        return try {
            retrofit.getTask(token, taskId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun updateTask(token: String, taskInfo: Task?) {
        try {
            retrofit.updateTask(token, taskInfo as DataTask)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getChildrenTask(token: String, childId: String): List<Task>? {
        return try {
            retrofit.getChildrenTasks(token, childId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getChildren(token: String): List<Profile>? {
        return try {
            retrofit.getChildren(token)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getChild(token: String, childId: String): Profile? {
        return try {
            retrofit.getChild(token, childId)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun addChild(token: String, profile: DataProfile): Boolean {
        return try {
            retrofit.addChild(token, profile)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}