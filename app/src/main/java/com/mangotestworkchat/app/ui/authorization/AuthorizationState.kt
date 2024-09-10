package com.mangotestworkchat.app.ui.authorization

import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse

sealed class AuthorizationState {
    data object InitState : AuthorizationState()
    data object UserExists : AuthorizationState()
    data object UserAbsent : AuthorizationState()
    data class AuthorizationCorrect (val data: CheckAuthCodeServerResponse) : AuthorizationState()
}