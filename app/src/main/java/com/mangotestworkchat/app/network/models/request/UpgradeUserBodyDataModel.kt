package com.mangotestworkchat.app.network.models.request

import com.google.gson.annotations.SerializedName

data class UpgradeUserBodyDataModel(
    val name: String? = null,
    val username: String? = null,
    val birthday: String? = null,
    val city: String? = null,
    val vk: String? = null,
    val instagram: String? = null,
    val status: String? = null,
    val avatar: Avatar? = null
)
data class Avatar(
    @SerializedName("base_64") val base64: String,
    val filename: String
)