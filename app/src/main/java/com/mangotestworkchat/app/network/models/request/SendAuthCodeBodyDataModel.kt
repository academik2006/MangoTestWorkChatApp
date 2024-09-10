package com.mangotestworkchat.app.network.models.request
import com.google.gson.annotations.SerializedName

data class SendAuthCodeBodyDataModel(
    @SerializedName ("phone") val phone: String
)