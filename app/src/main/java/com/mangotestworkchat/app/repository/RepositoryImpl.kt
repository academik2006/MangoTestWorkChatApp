package com.mangotestworkchat.app.repository

import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.di.scope.ApplicationScope
import com.mangotestworkchat.app.network.Network
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.network.api.handleApiError
import com.mangotestworkchat.app.network.api.handleSuccess
import com.mangotestworkchat.app.network.models.request.RefreshTokenBodyDataModel
import com.mangotestworkchat.app.network.models.request.RegisterUserBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.request.SendCheckAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.request.UpgradeUserBodyDataModel
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.CurrentUserServerResponse
import com.mangotestworkchat.app.network.models.response.SendAuthCodeServerResponse
import com.mangotestworkchat.app.network.models.response.UpgradeUserServerResponse
import com.mangotestworkchat.app.network.models.response.UserRefreshTokenServerResponse
import com.mangotestworkchat.app.network.models.response.UserRegisterServerResponse
import com.mangotestworkchat.app.ui.chats.ChatItemModel
import com.mangotestworkchat.app.utils.SharedPref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import retrofit2.Response
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
        val response = api.registerNewUser(registerUserBodyDataModel)
        return apiResultHandler(response)
    }

    override suspend fun checkAuthCode(
        phone: String,
        code: String
    ): ApiResult<CheckAuthCodeServerResponse> {
        val sendCheckAuthCodeBodyDataModel = SendCheckAuthCodeBodyDataModel(phone, code)
        val response = api.checkAuthCode(sendCheckAuthCodeBodyDataModel)
        return apiResultHandler(response)
    }

    override suspend fun refreshToken(): ApiResult<UserRefreshTokenServerResponse> {
        val refreshTokenBodyDataModel = RefreshTokenBodyDataModel(
            refreshToken = userDataTokenProfile?.refreshToken ?: throw Exception("No fresh token")
        )
        val response = api.refreshToken(refreshTokenBodyDataModel)
        return apiResultHandler(response)
    }

    override suspend fun sendAuthCode(phoneNumber: String): ApiResult<SendAuthCodeServerResponse> {
        val sendAuthCodeBodyDataModel = SendAuthCodeBodyDataModel(phoneNumber)
        val response = api.sendAuthCode(sendAuthCodeBodyDataModel)
        return apiResultHandler(response)
    }

    override suspend fun getCurrentUser(): ApiResult<CurrentUserServerResponse> {
        val response =
            api.getCurrentUser("Bearer ${userDataTokenProfile?.accessToken ?: throw Exception("No fresh token")}")
        return apiResultHandler(response)
    }

    override suspend fun upgradeCurrentUser(upgradeUserBodyDataModel: UpgradeUserBodyDataModel)
            : ApiResult<UpgradeUserServerResponse> {

        val response = api.upgradeUser(
            accessToken = "Bearer ${userDataTokenProfile?.accessToken ?: throw Exception("No fresh token")}",
            upgradeUserBodyDataModel = upgradeUserBodyDataModel
        )
        return apiResultHandler(response)
    }

    private fun <T : Any> apiResultHandler(response: Response<T>): ApiResult<T> {
        return try {
            if (response.isSuccessful) {
                handleSuccess(response)
            } else handleApiError(response)

        } catch (e: Exception) {
            ApiResult.Error(response.code(), e)
        }
    }

    override fun getChatsList(): List<ChatItemModel> {
        return listOf(
            ChatItemModel(0, icon = R.drawable.mashrooms, "Грибы", "Выезжаем в 5.00 утра", "4:49"),
            ChatItemModel(
                1,
                icon = R.drawable.sport,
                "Спорт",
                "Сегодня тренируемся в 21.00 утра",
                "10.25"
            )
        )
    }


}