package com.mangotestworkchat.app.network.models.request
import com.google.gson.annotations.SerializedName

data class RefreshTokenBodyDataModel(
    @SerializedName ("refresh_token") val refreshToken: String
)