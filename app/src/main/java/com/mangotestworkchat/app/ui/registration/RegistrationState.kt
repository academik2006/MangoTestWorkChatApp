package com.mangotestworkchat.app.ui.registration

import com.mangotestworkchat.app.network.models.response.UserRegisterServerResponse

sealed class RegistrationState {
    data object InitState : RegistrationState()
    data class SuccessRegisterNewUser (val data: UserRegisterServerResponse): RegistrationState()
    data class ErrorRegisterNewUser (val message: String) : RegistrationState()
}