package com.example.timemanager.data.local_data_base

import android.content.Context
import android.util.Log

class Settings(context: Context) {

    companion object {
        private const val PATH_FOR_TOKEN = "AUTH_TOKEN"
        private const val KEY_FOR_TOKEN = "TOKEN"
        private const val KEY_FOR_TYPE_ENTER_TIME = "KEY_FOR_TYPE_ENTER_TIME"
    }

    private val sharedPreferences =
        context.getSharedPreferences(PATH_FOR_TOKEN, Context.MODE_PRIVATE)

    fun saveToken(value: String) {
        Log.d("MyLog", "token $value")
        sharedPreferences.edit().putString(KEY_FOR_TOKEN, value).apply()
    }

    fun getToken(): String? {
        val token = sharedPreferences.getString(KEY_FOR_TOKEN, null)

        Log.d("MyLog", "token $token")
        return token
    }

    fun deleteToken() {
        sharedPreferences.edit().remove(KEY_FOR_TOKEN).apply()
    }
}