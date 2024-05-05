package com.example.timemanager.data.remote_data_base

import com.example.timemanager.data.DataProfile
import com.example.timemanager.data.DataTask
import com.example.timemanager.data.DataToken
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RemoteApi {

    @POST("users/register")
    suspend fun registration(
        @Body dataLogin: DataProfile,
    )

    @POST("users/login")
    suspend fun login(@Body dataLogin: DataProfile): DataToken?

    @GET("users/me")
    suspend fun getProfile(
        @Header("Authorization") token: String?,
    ): DataProfile

    @PUT("user/edit")
    suspend fun editProfile(
        @Header("Authorization") token: String?,
        @Body dataProfile: DataProfile,
    )

    @PUT("user/update_balance/{userId}/{reward}")
    suspend fun payReward(
        @Header("Authorization") token: String?,
        @Path("userId") userId: String,
        @Path("reward") reward: Float,
    )

    @POST("tasks/create/{childId}")
    suspend fun createTask(
        @Header("Authorization") token: String?,
        @Path("childId") childId: String,
        @Body task: DataTask,
    )

    @GET("tasks")
    suspend fun getAllTasks(
        @Header("Authorization") token: String?,
    ): List<DataTask>

    @GET("tasks/{taskId}")
    suspend fun getTask(
        @Header("Authorization") token: String?,
        @Path("taskId") taskId: String,
    ): DataTask

    @PUT("tasks/{taskId}")
    suspend fun updateTask(
        @Header("Authorization") token: String?,
        @Path("taskId") taskId: String,
        @Body task: DataTask,
    )

    @POST("/family/relations/create")
    suspend fun addChild(
        @Header("Authorization") token: String,
        @Body profile: DataProfile,
    )

    @GET("/family/children")
    suspend fun getChildren(
        @Header("Authorization") token: String?,
    ): List<DataProfile>

    @GET("/family/children/{childId}")
    suspend fun getChild(
        @Header("Authorization") token: String?,
        @Path("childId") childId: Int,
    ): DataProfile?

    @GET("/family/child/{relationId}")
    suspend fun getChildByRelationId(
        @Header("Authorization") token: String?,
        @Path("relationId") relationId: Int,
    ): DataProfile?

    @GET("family/children/{childId}/tasks")
    suspend fun getChildrenTasks(
        @Header("Authorization") token: String?,
        @Path("childId") childId: String,
    ): List<DataTask>
}