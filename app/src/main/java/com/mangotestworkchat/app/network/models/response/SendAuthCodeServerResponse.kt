package com.mangotestworkchat.app.network.models.response

import com.google.gson.annotations.SerializedName

data class SendAuthCodeServerResponse(
    @SerializedName("is_success") val isSuccess: Boolean
)