package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.CurrentUserServerResponse
import com.mangotestworkchat.app.network.models.response.SendAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterServerResponse


interface INetwork {
    suspend fun registerNewUser (
        name: String,
        phone: String,
        username: String
    ): ApiResult<UserRegisterServerResponse>
    suspend fun checkAuthCode(
        phoneNumber: String,
        code: String
    ): ApiResult<CheckAuthCodeServerResponse>

    suspend fun sendAuthCode(
        phoneNumber: String,
    ): ApiResult<SendAuthCodeServerResponse>
    suspend fun getCurrentUser(): ApiResult<CurrentUserServerResponse>
}