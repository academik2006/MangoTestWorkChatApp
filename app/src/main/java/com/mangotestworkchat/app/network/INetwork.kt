package com.mangotestworkchat.app.network

import com.mangotestworkchat.app.network.models.ApiAnswer


interface INetwork {
    suspend fun checkAuthCode(
        phoneNumber: String,
        code: String
    ): ApiAnswer

    suspend fun sendAuthCode(
        phoneNumber: String,
    ): ApiAnswer
}