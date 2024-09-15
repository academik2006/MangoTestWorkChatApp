package com.mangotestworkchat.app.network.api

import com.granch.network.models.api.ParseApiError
import retrofit2.Response

sealed class ApiResult<out T> {
    data class Success<out T>(val successData : T) : ApiResult<T>()
    class Error(val code: Int, val exception: java.lang.Exception, val message: String = exception.localizedMessage)
        : ApiResult<Nothing>()
}

fun <T : Any> handleApiError(resp: Response<T>): ApiResult.Error {
    val error = ParseApiError.parseError(resp)
    return ApiResult.Error(resp.code(), exception = Exception(error.message), message = "Запрос не выполнен. Код ошибки: ${resp.code()}, описание ${resp.message()}")
}

fun <T : Any> handleSuccess(response: Response<T>): ApiResult<T> {
    response.body()?.let {
        return ApiResult.Success(it)
    } ?: return handleApiError(response)
}