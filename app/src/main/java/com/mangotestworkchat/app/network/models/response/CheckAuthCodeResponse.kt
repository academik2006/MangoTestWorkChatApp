package com.mangotestworkchat.app.network.models.response

import com.google.gson.annotations.SerializedName
import com.mangotestworkchat.app.data.UserDataToken

data class CheckAuthCodeResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("is_user_exists") val isUserExists: Boolean,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("user_id") val userId: Int
) {
    fun toUserDataToken () : UserDataToken {
        return UserDataToken(
            accessToken, refreshToken, userId
        )
    }
}