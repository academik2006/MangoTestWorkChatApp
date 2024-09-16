package com.mangotestworkchat.app.utils

import android.content.Context
import javax.inject.Inject

const val FILE_SHARED_PREFERENCE_NAME = "mangoTestWorkChatAppFile"
const val ACCESS_TOKEN_USER = "access token user"
class SharedPref @Inject constructor () {
    fun saveAccessToken (context: Context, accessToken: String) {
        val sharedPref = context.getSharedPreferences(FILE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()
        editor?.putString(ACCESS_TOKEN_USER, accessToken)
        editor?.apply()
    }
    fun getAccessToken (context: Context) : String? {
        val sharedPref = context.getSharedPreferences (FILE_SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        return sharedPref?.getString(ACCESS_TOKEN_USER, null)
    }
}