package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeResponse
import com.mangotestworkchat.app.network.models.response.SendAuthCodeResponse
import com.mangotestworkchat.app.network.models.response.ValidationErrorResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterResponse


interface INetwork {
    suspend fun registerNewUser (
        name: String,
        phone: String,
        username: String
    ): ApiResult<UserRegisterResponse>
    suspend fun checkAuthCode(
        phoneNumber: String,
        code: String
    ): ApiResult<CheckAuthCodeResponse>

    suspend fun sendAuthCode(
        phoneNumber: String,
    ): ApiResult<SendAuthCodeResponse>
}