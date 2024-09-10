package com.mangotestworkchat.app.ui.registration

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.network.INetwork
import com.mangotestworkchat.app.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    repository: Repository,
    private val iNetwork: INetwork
) : ViewModelBase(repository) {

    val state = MutableStateFlow<RegistrationState>(RegistrationState.InitState)

    fun validateLatinLetters(input: String): Boolean {
        val regex = Regex("^[A-Za-z0-9]{6,}$")
        return regex.matches(input)
    }

    fun registerNewUserVM(
        phone: String,
        name: String,
        userName: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result =
                iNetwork.registerNewUser(name = name, phone = phone, username = userName)) {
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "registerNewUserVM: получен ответ на запрос регистрации нового пользователя ${result.successData}"
                    )
                    state.emit(RegistrationState.SuccessRegisterNewUser(result.successData))
                }
                is ApiResult.Error -> {
                    state.emit(RegistrationState.ErrorRegisterNewUser(result.message))
                }
            }
        }
    }
}

fun isContainsSpecialCharacters(text: String): Boolean {
    return text.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())
}

