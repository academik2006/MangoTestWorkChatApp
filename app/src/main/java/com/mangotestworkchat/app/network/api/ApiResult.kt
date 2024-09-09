package com.granch.network.models.api
import retrofit2.Response

sealed class ApiResult<out T> {
    data class Success<out T>(val successData : T) : ApiResult<T>()
    class Error(val exception: java.lang.Exception, val message: String = exception.localizedMessage)
        : ApiResult<Nothing>()
}

fun <T : Any> handleApiError(resp: Response<T>): ApiResult.Error {
    val error = ParseApiError.parseError(resp)
    return ApiResult.Error(Exception(error.message))
}

fun <T : Any> handleSuccess(response: Response<T>): ApiResult<T> {
    response.body()?.let {
        return ApiResult.Success(it)
    } ?: return handleApiError(response)
}