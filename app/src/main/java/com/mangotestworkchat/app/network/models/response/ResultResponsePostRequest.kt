package com.granch.network.models.response

data class ResultResponsePostRequest(
    val detail: String,
    val status: Int,
    val title: String,
    val traceId: String,
    val type: String
) : ResultResponseBase()