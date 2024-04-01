package com.example.timemanager.data.remote_data_base

import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.DataToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface RemoteApi {

    @POST("users/register")
    suspend fun registration(
        @Body dataLogin: DataProfile
    )

    @POST("users/login")
    suspend fun login(@Body dataLogin: DataProfile): DataToken?

    @GET("users/me")
    suspend fun getProfile(
        @Header("Authorization") token: String?
    ): DataProfile

    @PUT("user/edit")
    suspend fun editProfile(
        @Header("Authorization") token: String?,
        @Body dataProfile: DataProfile
    )

    @POST("tasks/create")
    suspend fun createTask(
        @Header("Authorization") token: String?,
        @Body task: DataTask
    )

    @GET("tasks")
    suspend fun getAllTasks(
        @Header("Authorization") token: String?
    ): List<DataTask>

}