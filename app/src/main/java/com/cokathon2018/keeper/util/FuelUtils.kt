package com.cokathon2018.keeper.util

import android.content.Context
import com.cokathon2018.keeper.MainActivity
import com.cokathon2018.keeper.RecordResponse
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson

class FuelUtils (private val c: Context){
    init {
        FuelManager.instance.basePath = "http://10.0.2.2:8080/api"
    }

    fun getOutDate(username : String) {
        val gson = Gson()
        val resultJson = RecordResponse(0, "error", "", 0)
        "/printouting.php?username=$username".httpGet().responseJson { _, _, result ->
            when (result) {
                is Result.Failure -> {
                    (c as MainActivity).recordFragment.notifyFinish("["+gson.toJson(resultJson)+"]")
                }
                is Result.Success -> {
                    (c as MainActivity).recordFragment.notifyFinish(result.get().content)
                }
            }
        }
    }
}
