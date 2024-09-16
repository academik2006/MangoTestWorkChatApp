package com.mangotestworkchat.app.ui.authorization

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.utils.MaskTransformation
import com.mangotestworkchat.app.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val repository: Repository,
    sharedPref: SharedPref
) : ViewModelBase(repository, sharedPref) {

    val state = MutableStateFlow<AuthorizationState>(AuthorizationState.InitState)

    private fun createCountryMaskDataListVM(): List<PhoneMaskCountryData> {
        return listOf(
            PhoneMaskCountryData(country = "Russia", mask = "+7 (9##) ###-##-##", maxChar = 9, prefix = "+79"),
            PhoneMaskCountryData(country = "Belarus", mask = "+375 (###) ###-##-##", maxChar = 10, prefix = "+375")
        )
    }

    fun findMaskVM(country: String): PhoneMaskCountryData {
        return createCountryMaskDataListVM().find {
            it.country == country
        } ?: throw Exception("invalidate key")
    }

    fun getMaskTransformationVM(maskText: String): MaskTransformation {
        return MaskTransformation(maskText)
    }

    fun sendAuthVM(context: Context, userPhoneCurrent: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = repository.sendAuthCode(userPhoneCurrent)) {
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "sendAuthCode: получен ответ на запрос авторизации ${result.successData}"
                    )
                    if (result.successData.isSuccess) {
                        state.emit(AuthorizationState.UserExists)
                    } else state.emit(AuthorizationState.UserAbsent)
                }
                is ApiResult.Error -> {
                    showToastToUser(context, result.message)
                }
            }
        }
    }
    fun checkAuthCodeVM(context: Context, userPhoneCurrent: String, code: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = repository.checkAuthCode(userPhoneCurrent, code)){
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "checkAuthCodeVM: получен ответ на проверку кода авторизации ${result.successData}"
                    )
                    if (result.successData.isUserExists) {
                        state.emit(AuthorizationState.AuthorizationCorrect(result.successData))
                    } else state.emit(AuthorizationState.UserAbsent)
                }
                is ApiResult.Error -> {
                    showToastToUser(context, result.message)
                }
            }
        }
    }
}