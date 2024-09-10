package com.mangotestworkchat.app.ui.profile

import com.mangotestworkchat.app.data.CurrentUserProfileData
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeServerResponse

sealed class ProfileState {
    data object InitState : ProfileState()
    data class SuccessLoadProfileData (val data: CurrentUserProfileData) : ProfileState()
    data object ErrorLoadProfileData : ProfileState()
}