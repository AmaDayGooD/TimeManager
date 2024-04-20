package com.example.timemanager.data.local_data_base

import android.content.Context

class Settings(context: Context) {

    companion object {
        private const val PATH_FOR_TOKEN = "AUTH_TOKEN"
        private const val KEY_FOR_TOKEN = "TOKEN"
        private const val KEY_FOR_TYPE_ENTER_TIME = "KEY_FOR_TYPE_ENTER_TIME"
    }

    private val sharedPreferences =
        context.getSharedPreferences(PATH_FOR_TOKEN, Context.MODE_PRIVATE)

    fun saveToken(value: String) {
        sharedPreferences.edit().putString(KEY_FOR_TOKEN, value).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_FOR_TOKEN, null)
    }

    fun deleteToken() {
        sharedPreferences.edit().remove(KEY_FOR_TOKEN).apply()
    }
}