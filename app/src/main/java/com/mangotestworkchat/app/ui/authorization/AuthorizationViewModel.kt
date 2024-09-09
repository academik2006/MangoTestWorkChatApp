package com.mangotestworkchat.app.ui.authorization

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.granch.network.models.api.ApiResult
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.network.INetwork
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.utils.MaskTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val repository: Repository,
    private val iNetwork: INetwork
) : ViewModelBase() {

    private fun createCountryMaskDataListVM(): List<PhoneMaskCountryData> {
        return listOf(
            PhoneMaskCountryData(country = "Russia", mask = "+7 (9##) ###-##-##", maxChar = 9),
            PhoneMaskCountryData(country = "Belarus", mask = "+375 (###) ###-##-##", maxChar = 10)
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

    fun checkAuthVM(context: Context, userPhoneCurrent: String) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = iNetwork.sendAuthCode(userPhoneCurrent)) {
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "sendAuthCode: получен ответ на запрос авторизации ${result.successData}"
                    )
                    //_contactList.emit(contactList)
                }
                is ApiResult.Error -> {
                    //_contactList.emit(listOf())
                    showToastToUser(context, result.message)
                }
            }
        }
    }
}