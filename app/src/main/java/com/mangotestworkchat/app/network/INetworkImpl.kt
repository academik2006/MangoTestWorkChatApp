package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.models.request.SendAuthCodeBodyDataModel
import com.mangotestworkchat.app.network.models.ApiAnswer
import com.mangotestworkchat.app.network.models.response.ResultResponseBase
import com.mangotestworkchat.app.network.models.response.SendAuthCodeResponse
import javax.inject.Inject

class INetworkImpl @Inject constructor() : INetwork {

    private val api = Network().getApiServer()

    sealed class TypeDataRequest {
        data object SendAuthCode : TypeDataRequest()
        data object CheckAuthCode : TypeDataRequest()
    }

    override suspend fun checkAuthCode(phoneNumber: String, code: String): ApiAnswer {
        TODO("Not yet implemented")
    }

    override suspend fun sendAuthCode(phoneNumber: String): ApiAnswer {
        val sendAuthCodeBodyDataModel = SendAuthCodeBodyDataModel(phoneNumber)
        return getResultDataFromServer(
            typeData = TypeDataRequest.SendAuthCode,
            sendAuthCodeBodyDataModel = sendAuthCodeBodyDataModel
        )
    }

    /**
     * Обработчик запросов к серверу
     */
    private suspend fun getResultDataFromServer(
        typeData: TypeDataRequest,
        sendAuthCodeBodyDataModel: SendAuthCodeBodyDataModel? = null
    ): ApiAnswer {
        try {
            val result = when (typeData) {
                TypeDataRequest.SendAuthCode -> api.sendAuthCode(sendAuthCodeBodyDataModel ?: throw Exception ("No body for request"))
                TypeDataRequest.CheckAuthCode -> api.checkAuthCode(sendAuthCodeBodyDataModel ?: throw Exception ("No body for request"))
            }
            if (result.isSuccessful) {
                val resultBody = when (typeData) {
                    TypeDataRequest.SendAuthCode -> result.body() as SendAuthCodeResponse
                    else -> null
                }
                val answerPost = when (typeData) {
                    TypeDataRequest.CheckAuthCode -> result.body() as ResultResponseBase
                    else -> null
                }

                return ApiAnswer(
                    result = result.code(),
                    message = result.message(),
                    body = resultBody,
                    answerPost = answerPost
                )

            } else {
                return ApiAnswer(result.code(), result.errorBody().toString())
            }

        } catch (e: Exception) {
            return ApiAnswer(0, e.message.toString())
        }
    }



}