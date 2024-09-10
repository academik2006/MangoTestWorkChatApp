package com.mangotestworkchat.app.ui.registration

import com.mangotestworkchat.app.network.models.response.UserRegisterResponse

sealed class RegistrationState {
    data object InitState : RegistrationState()
    data class SuccessRegisterNewUser (val data: UserRegisterResponse): RegistrationState()
    data class ErrorRegisterNewUser (val message: String) : RegistrationState()
}