package com.mangotestworkchat.app.network.models.response

import com.google.gson.annotations.SerializedName
import com.mangotestworkchat.app.data.CurrentUserProfileData

data class CurrentUserServerResponse(
    @SerializedName ("profile_data") val profileData: ProfileData
) {
    fun toCurrentUserProfileData () : CurrentUserProfileData {
        return CurrentUserProfileData(
            avatar = profileData.avatar,
            phone = profileData.phone,
            name = profileData.name,
            userName = profileData.username,
            city = profileData.city,
            birthday = profileData.birthday
        )
    }
}
data class ProfileData(
    val avatar: String,
    val avatars: Avatars,
    val birthday: String,
    val city: String,
    @SerializedName ("completed_task") val completedTask: Int,
    val created: String,
    val id: Int,
    val instagram: String,
    val last: String,
    val name: String,
    val online: Boolean,
    val phone: String,
    val status: String,
    val username: String,
    val vk: String
)
