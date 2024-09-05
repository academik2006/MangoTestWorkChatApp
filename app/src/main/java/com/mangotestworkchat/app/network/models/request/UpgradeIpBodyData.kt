package com.granch.network.models.request

data class UpgradeIpBodyData(
    val number: Int,
    val ip: String,
    val bsip: String
)