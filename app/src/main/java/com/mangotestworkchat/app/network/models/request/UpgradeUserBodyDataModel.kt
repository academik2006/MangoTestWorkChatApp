package com.mangotestworkchat.app.network.models.request

import com.google.gson.annotations.SerializedName

data class UpgradeUserBodyDataModel(
    val avatar: Avatar? = null,
    val birthday: String = "07.04.1985",
    val city: String? = null,
    val instagram: String? = null,
    val name: String? = null,
    val status: String? = null,
    val username: String? = null,
    val vk: String? = null
)

data class Avatar(
    @SerializedName("base_64") val base64: String,
    val filename: String
)