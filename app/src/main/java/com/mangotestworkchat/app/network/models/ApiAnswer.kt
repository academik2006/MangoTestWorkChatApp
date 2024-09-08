package com.mangotestworkchat.app.network.models

import com.mangotestworkchat.app.network.models.response.ResultResponseBase

data class ApiAnswer(
    val result: Int,
    val message: String,
    val body: ResultResponseBase? = null,
    val answerPost: ResultResponseBase? = null
)
