package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.models.request.RegisterUserBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendCheckAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeResponse
import com.mangotestworkchat.app.network.models.response.ResultResponseBase
import com.mangotestworkchat.app.network.models.response.SendAuthCodeResponse
import com.mangotestworkchat.app.network.models.response.ValidationErrorResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServer {

    @POST("api/v1/users/register/")
    suspend fun registerNewUser(
        @Body registerUserBodyDataModel: RegisterUserBodyDataModel
    ): Response<UserRegisterResponse>

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body sendAuthCodeBodyDataModel: SendAuthCodeBodyDataModel
    ): Response<SendAuthCodeResponse>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body sendCheckAuthCodeBodyDataModel: SendCheckAuthCodeBodyDataModel
    ): Response<CheckAuthCodeResponse>

}