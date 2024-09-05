package com.mangotestworkchat.app.network.models

import com.granch.network.models.response.ResultResponseBase

data class ApiAnswer(
    val result: Int,
    val message: String,
    val body: List<ResultResponseBase>? = null,
    val answerPost: ResultResponseBase? = null
)
