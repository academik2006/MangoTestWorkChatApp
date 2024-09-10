package com.mangotestworkchat.app.network.models.response

data class ErrorResponseBody (
    val protocol: String,
    val code: Int,
    val message: String,
    val url: String
)