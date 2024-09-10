package com.mangotestworkchat.app.network.models.request

data class RegisterUserBodyDataModel(
    val name: String,
    val phone: String,
    val username: String
)