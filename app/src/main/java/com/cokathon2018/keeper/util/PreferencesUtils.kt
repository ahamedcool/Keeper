package com.cokathon2018.keeper.util

import android.app.Activity
import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE

class PreferencesUtils(c: Activity){
    private var pref : SharedPreferences? = null
    init {
        pref = c.getSharedPreferences("pref", MODE_PRIVATE)
    }
    fun saveData(key : String , value: String){
        pref!!.edit().apply{
            putString(key, value)
            apply()
        }
    }
    fun getData(key : String) : String{
        return pref!!.getString(key, "")
    }
}