package com.mangotestworkchat.app.network

import com.granch.network.Network
import com.granch.network.models.request.GivePhonePathData
import com.granch.network.models.request.UpgradeIpBodyData
import com.granch.network.models.response.ResultResponsePhoneServer
import com.granch.network.models.response.ResultResponseUpgradeIp
import com.mangotestworkchat.app.network.models.ApiAnswer

object IModuleImpl : IModule {

    private val api = Network().getApiServer()

    sealed class TypeDataRequest {
        data object AllPhones : TypeDataRequest()
        data object UpgradeIP : TypeDataRequest()
        data object GivePhone : TypeDataRequest()
    }

    override suspend fun getPhoneListFromServer(): ApiAnswer {
        return getResultDataFromServer(TypeDataRequest.AllPhones)
    }

    override suspend fun givePhone(
        phoneId: Int,
        employeerId: Int
    ): ApiAnswer {
        val givePhonePathData = GivePhonePathData (phoneId, employeerId)
        return getResultDataFromServer(
            typeData = TypeDataRequest.GivePhone,
            givePhonePathData = givePhonePathData
        )
    }

    override suspend fun upgradeIp(
        number: Int,
        ip: String,
        bsip: String
    ): ApiAnswer {
        val upgradeIpBodyData = UpgradeIpBodyData(number, ip, bsip)
        return getResultDataFromServer(
            typeData = TypeDataRequest.UpgradeIP,
            upgradeIpBodyData = upgradeIpBodyData
        )
    }

    /**
     * Обработчик запросов к серверу
     */
    private suspend fun getResultDataFromServer(
        typeData: TypeDataRequest,
        upgradeIpBodyData: UpgradeIpBodyData? = null,
        givePhonePathData: GivePhonePathData? = null
    ): ApiAnswer {
        try {
            val result = when (typeData) {
                TypeDataRequest.AllPhones -> api.getPhoneList()
                TypeDataRequest.UpgradeIP -> api.upgradeIp(upgradeIpBodyData ?: throw Exception ("No body for request"))
                TypeDataRequest.GivePhone -> api.givePhone(givePhonePathData?.phoneId , givePhonePathData?.employeeId)
            }
            if (result.isSuccessful) {
                val resultBody = when (typeData) {
                    TypeDataRequest.AllPhones -> result.body() as List<ResultResponsePhoneServer>
                    else -> null
                }
                val answerPost = when (typeData) {
                    TypeDataRequest.UpgradeIP -> result.body() as ResultResponseUpgradeIp
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