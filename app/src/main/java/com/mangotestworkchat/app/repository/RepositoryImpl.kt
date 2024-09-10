package com.mangotestworkchat.app.repository

import com.mangotestworkchat.app.data.UserDataToken
import com.mangotestworkchat.app.di.scope.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class RepositoryImpl @Inject constructor() : Repository {

    private var userDataTokenProfile: UserDataToken? = null
    override fun saveUserDataToken(userDataToken: UserDataToken) {
        userDataTokenProfile = userDataToken
    }


}