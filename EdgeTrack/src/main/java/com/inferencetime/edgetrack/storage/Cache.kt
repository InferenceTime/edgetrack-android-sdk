package com.inferencetime.edgetrack.storage

import android.content.Context
import android.content.SharedPreferences

object Cache {
    private const val PREF_NAME = "EdgeTrack"
    private lateinit var sharedPreferences: SharedPreferences

    private const val API_KEY = "apiKey"
    private const val SESSION_UUID = "sessionUuid"

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun saveApiKey(apiKey: String) {
        saveString(API_KEY, apiKey)
    }

    fun saveSessionUuid(sessionUuid: String) {
        saveString(SESSION_UUID, sessionUuid)
    }

    fun getApiKey(): String? {
        return getString(API_KEY, null)
    }

    fun getSessionUuid(): String? {
        return getString(SESSION_UUID, null)
    }


}