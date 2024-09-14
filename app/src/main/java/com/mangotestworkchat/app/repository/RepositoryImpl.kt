package com.mangotestworkchat.app.repository

import android.util.Log
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.di.scope.ApplicationScope
import com.mangotestworkchat.app.network.Network
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.network.api.handleApiError
import com.mangotestworkchat.app.network.api.handleSuccess
import com.mangotestworkchat.app.network.models.request.RegisterUserBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendCheckAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.CurrentUserServerResponse
import com.mangotestworkchat.app.network.models.response.SendAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterServerResponse
import com.mangotestworkchat.app.ui.chats.ChatItemModel
import javax.inject.Inject

@ApplicationScope
class RepositoryImpl @Inject constructor() : Repository {

    private var userDataTokenProfile: UserDataToken? = null
    private val api = Network().getApiServer()
    override fun saveUserDataToken(userDataToken: UserDataToken) {
        userDataTokenProfile = userDataToken
    }


    override suspend fun registerNewUser(
        name: String,
        phone: String,
        username: String
    ): ApiResult<UserRegisterServerResponse> {
        val registerUserBodyDataModel = RegisterUserBodyDataModel(name, phone, username)
        return try {
            val response = api.registerNewUser(registerUserBodyDataModel)
            Log.d(APP_LOG, "registerNewUser: получен ответ на запрос ${response.toString()}")
            if (response.isSuccessful) {
                handleSuccess(response)
            } else handleApiError(response)

        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun checkAuthCode(phone: String, code: String): ApiResult<CheckAuthCodeServerResponse> {
        val sendCheckAuthCodeBodyDataModel = SendCheckAuthCodeBodyDataModel(phone, code)
        return try {
            val response = api.checkAuthCode (sendCheckAuthCodeBodyDataModel)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else handleApiError(response)

        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun sendAuthCode(phoneNumber: String): ApiResult<SendAuthCodeServerResponse> {
        val sendAuthCodeBodyDataModel = SendAuthCodeBodyDataModel(phoneNumber)
        return try {
            val response = api.sendAuthCode(sendAuthCodeBodyDataModel)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else handleApiError(response)

        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getCurrentUser(): ApiResult<CurrentUserServerResponse> {
        return try {
            val response = api.getCurrentUser("Bearer ${userDataTokenProfile?.accessToken ?: throw Exception("No fresh token")}")
            Log.d(APP_LOG, "getCurrentUser: получен ответ на запрос данных пользователя $response")
            if (response.isSuccessful) {
                handleSuccess(response)
            } else handleApiError(response)
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override fun getChatsList(): List<ChatItemModel> {
        return listOf(
            ChatItemModel(0,icon = R.drawable.mashrooms, "Грибы", "Выезжаем в 5.00 утра", "4:49"),
            ChatItemModel(1, icon = R.drawable.sport, "Спорт", "Сегодня тренируемся в 21.00 утра", "10.25")
        )
    }


}