package com.mangotestworkchat.app.repository

import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.CurrentUserServerResponse
import com.mangotestworkchat.app.network.models.response.SendAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.UserRefreshTokenServerResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterServerResponse
import com.mangotestworkchat.app.ui.chats.ChatItemModel

interface Repository {
    fun saveUserDataToken(userDataToken: UserDataToken)

    suspend fun registerNewUser(
        name: String,
        phone: String,
        username: String
    ): ApiResult<UserRegisterServerResponse>

    suspend fun checkAuthCode(
        phone: String,
        code: String
    ): ApiResult<CheckAuthCodeServerResponse>

    suspend fun refreshToken(
        refreshToken: String
    ): ApiResult<UserRefreshTokenServerResponse>

    suspend fun sendAuthCode(
        phoneNumber: String,
    ): ApiResult<SendAuthCodeServerResponse>

    suspend fun getCurrentUser(): ApiResult<CurrentUserServerResponse>
    fun getChatsList(): List<ChatItemModel>
}