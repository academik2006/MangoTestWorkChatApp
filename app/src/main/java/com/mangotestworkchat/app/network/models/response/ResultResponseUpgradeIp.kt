package com.granch.network.models.response

data class ResultResponseUpgradeIp (
    val id: Int,
    val phoneId: Int,
    val ip: String,
    val bsip: String,
    val registrationTime: String
) : ResultResponseBase()