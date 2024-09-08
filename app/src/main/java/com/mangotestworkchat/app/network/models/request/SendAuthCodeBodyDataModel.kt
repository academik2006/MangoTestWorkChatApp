package com.mangotestworkchat.app.network.models.request

data class SendAuthCodeBodyDataModel(
    val phoneNumber: String,
    val code: String? = null
)