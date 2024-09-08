package com.mangotestworkchat.app.network.models.response

data class SendAuthCodeResponse(
    val loc: List<String>,
    val msg: String,
    val type: String
) : ResultResponseBase()