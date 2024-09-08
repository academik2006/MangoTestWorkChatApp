package com.granch.network.models.response

import com.mangotestworkchat.app.network.models.response.ResultResponseBase

data class ResultResponsePostRequest(
    val detail: String,
    val status: Int,
    val title: String,
    val traceId: String,
    val type: String
) : ResultResponseBase()