package com.granch.network.models.api

import android.util.Log
import com.google.gson.GsonBuilder
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.network.api.APIError
import retrofit2.Response
import java.io.IOException

object ParseApiError {
    fun parseError(response: Response<*>): APIError {
        val gson = GsonBuilder().create()
        val error: APIError

        try {
            error = gson.fromJson(response.errorBody()?.string(), APIError::class.java)
        } catch (e: IOException) {
            e.message?.let {
                Log.d(APP_LOG, "parseError: $it")
            }
            return APIError()
        }
        return error
    }

}