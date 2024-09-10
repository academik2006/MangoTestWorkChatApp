package com.mangotestworkchat.app.network.models.response

import com.google.gson.annotations.SerializedName
import com.mangotestworkchat.app.data.UserDataToken

data class UserRegisterServerResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("user_id") val userId: Int
) {
    fun toUserDataToken () : UserDataToken {
        return UserDataToken(accessToken, refreshToken, userId)
    }
}