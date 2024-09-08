package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.response.ResultResponseBase
import com.mangotestworkchat.app.network.models.response.SendAuthCodeResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServer {

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body sendAuthCodeBodyDataModel: SendAuthCodeBodyDataModel
    ): Response<SendAuthCodeResponse>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body sendAuthCodeBodyDataModel: SendAuthCodeBodyDataModel

    ): Response<ResultResponseBase>

}