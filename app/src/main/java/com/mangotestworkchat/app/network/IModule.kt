package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.models.ApiAnswer


interface IModule {
    suspend fun getPhoneListFromServer()
    : ApiAnswer

    suspend fun givePhone(
        phoneId: Int,
        employeerId: Int
    ) : ApiAnswer

    suspend fun upgradeIp(
        number: Int,
        ip: String,
        bsip: String
    ) : ApiAnswer
}