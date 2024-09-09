package com.mangotestworkchat.app.network

import com.granch.network.models.api.ApiResult
import com.mangotestworkchat.app.network.models.ApiAnswer
import com.mangotestworkchat.app.network.models.response.SendAuthCodeResponse


interface INetwork {
    suspend fun checkAuthCode(
        phoneNumber: String,
        code: String
    ): ApiResult<Nothing>

    suspend fun sendAuthCode(
        phoneNumber: String,
    ): ApiResult<List<SendAuthCodeResponse>>
}