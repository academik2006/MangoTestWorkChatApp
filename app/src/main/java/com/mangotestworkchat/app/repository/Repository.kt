package com.mangotestworkchat.app.repository

import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.network.models.response.CheckAuthCodeResponse

interface Repository {
    fun saveUserDataToken (userDataToken: UserDataToken)
}