package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.models.request.RegisterUserBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendCheckAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.CurrentUserServerResponse
import com.mangotestworkchat.app.network.models.response.SendAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterServerResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiServer {

    @POST("api/v1/users/register/")
    suspend fun registerNewUser(
        @Body registerUserBodyDataModel: RegisterUserBodyDataModel
    ): Response<UserRegisterServerResponse>

    @POST("api/v1/users/send-auth-code/")
    suspend fun sendAuthCode(
        @Body sendAuthCodeBodyDataModel: SendAuthCodeBodyDataModel
    ): Response<SendAuthCodeServerResponse>

    @POST("api/v1/users/check-auth-code/")
    suspend fun checkAuthCode(
        @Body sendCheckAuthCodeBodyDataModel: SendCheckAuthCodeBodyDataModel
    ): Response<CheckAuthCodeServerResponse>

    @GET("api/v1/users/me/")
    suspend fun getCurrentUser(): Response<CurrentUserServerResponse>

}