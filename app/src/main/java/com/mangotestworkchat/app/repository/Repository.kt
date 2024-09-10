package com.mangotestworkchat.app.repository

import com.mangotestworkchat.app.data.UserDataToken

interface Repository {
    fun saveUserDataToken (userDataToken: UserDataToken)
}