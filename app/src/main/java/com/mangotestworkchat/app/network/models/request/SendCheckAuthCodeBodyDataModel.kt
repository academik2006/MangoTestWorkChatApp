package com.mangotestworkchat.app.network.models.request
import com.google.gson.annotations.SerializedName

data class SendCheckAuthCodeBodyDataModel(
    @SerializedName ("phone") val phone: String,
    @SerializedName ("code") val code: String
)