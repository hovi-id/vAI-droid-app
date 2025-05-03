package com.goodwy.dialer.utils

import android.content.Context
import androidx.core.content.edit
import com.goodwy.dialer.reponse.Connection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferencesUtils {

    private const val PHONE_NUMBER = "PHONE_NUMBER"
    private const val TOKEN = "TOKEN"
    private const val CONNECTIONS = "CONNECTIONS"
    private const val PREFS = "PREFS"

    fun savePhoneNumber(context: Context, value: String) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit() { putString(PHONE_NUMBER, value) }
    }

    fun getPhoneNumber(context: Context):String? {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getString(PHONE_NUMBER, null)
    }

    fun saveToken(context: Context, value: String) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit() { putString(TOKEN, value) }
    }

    fun getToken(context: Context):String? {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getString(TOKEN, null)
    }

    fun saveConnections(context: Context, value: List<Connection>) {



        val gson = Gson()
        val json = gson.toJson(value) // Convert list to JSON


        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit() { putString(CONNECTIONS, json) }
    }

    fun getConnections(context: Context):List<Connection> {


        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val gson = Gson()
        val json = prefs.getString(CONNECTIONS, null)
//        val json = sharedPrefs.getString("connections_list", null)

        val type = object : TypeToken<List<Connection>>() {}.type
        return if (json != null) gson.fromJson(json, type) else emptyList()


//        return prefs.getString(CONNECTIONS, null)

    }


//
//    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        return prefs.getInt(key, defaultValue)
//    }
//
//    fun saveInt(context: Context, key: String, value: Int) {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        prefs.edit() { putInt(key, value) }
//    }
//
//    fun getInt(context: Context, key: String, defaultValue: Int = 0): Int {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        return prefs.getInt(key, defaultValue)
//    }
//
//    fun saveLong(context: Context, key: String, value: Long) {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        prefs.edit() { putLong(key, value) }
//    }
//
//    fun getLong(context: Context, key: String, defaultValue: Long = 0L): Long {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        return prefs.getLong(key, defaultValue)
//    }
//
//    fun saveFloat(context: Context, key: String, value: Float) {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        prefs.edit() { putFloat(key, value) }
//    }
//
//    fun getFloat(context: Context, key: String, defaultValue: Float = 0f): Float {
//        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        return prefs.getFloat(key, defaultValue)
//    }
}
