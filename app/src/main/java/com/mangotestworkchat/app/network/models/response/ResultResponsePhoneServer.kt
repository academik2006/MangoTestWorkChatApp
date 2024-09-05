package com.granch.network.models.response

data class ResultResponsePhoneServer(
    val attachementInfo: AttachementInfo,
    val ip: String
) : ResultResponseBase()

data class AttachementInfo(
    private val attached: String,
    private val detached: String,
    private val employeeId: Int,
    private val id: Int,
    private val phoneId: Int,
    val phoneNum: Int
)
