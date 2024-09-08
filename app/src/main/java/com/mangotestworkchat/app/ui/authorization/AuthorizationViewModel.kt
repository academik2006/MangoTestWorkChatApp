package com.mangotestworkchat.app.ui.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.network.INetwork
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.utils.MaskTransformation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val repository: Repository,
    private val iNetwork: INetwork
) : ViewModel() {

    private fun createCountryMaskDataListVM(): List<PhoneMaskCountryData> {
        return listOf(
            PhoneMaskCountryData(country = "Russia", mask = "+7 (9##) ###-##-##", maxChar = 9),
            PhoneMaskCountryData(country = "Belarus", mask = "+375 (###) ###-##-##", maxChar = 10)
        )
    }

    fun findMaskVM(country: String): PhoneMaskCountryData {
        return createCountryMaskDataListVM().find {
            it.country == country
        } ?: throw Exception ("invalidate key")
    }

    fun getMaskTransformationVM(maskText: String): MaskTransformation {
        return MaskTransformation(maskText)
    }

    fun checkAuthVM(userPhoneCurrent: String) {
        viewModelScope.launch (Dispatchers.IO) {
            iNetwork.sendAuthCode(userPhoneCurrent)
        }

    }

}