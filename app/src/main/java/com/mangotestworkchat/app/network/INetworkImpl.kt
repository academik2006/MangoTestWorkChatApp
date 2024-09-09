package com.mangotestworkchat.app.network

import com.granch.network.models.api.ApiResult
import com.granch.network.models.api.handleApiError
import com.granch.network.models.api.handleSuccess
import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.ApiAnswer
import com.mangotestworkchat.app.network.models.response.ResultResponseBase
import com.mangotestworkchat.app.network.models.response.SendAuthCodeResponse
import javax.inject.Inject

class INetworkImpl @Inject constructor() : INetwork {

    private val api = Network().getApiServer()

    override suspend fun checkAuthCode(phoneNumber: String, code: String): ApiResult<Nothing> {
        TODO("Not yet implemented")
    }

    override suspend fun sendAuthCode(phoneNumber: String): ApiResult<List<SendAuthCodeResponse>> {
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

}