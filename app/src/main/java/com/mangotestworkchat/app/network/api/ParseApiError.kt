package com.granch.network.models.api

import com.google.gson.GsonBuilder
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
                //вывести в лог
            }
            return APIError()
        }
        return error
    }

}