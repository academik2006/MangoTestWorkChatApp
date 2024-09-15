package com.mangotestworkchat.app.ui.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: Repository
) : ViewModelBase(repository) {

    val state = MutableStateFlow<ProfileState>(ProfileState.InitState)

    fun getCurrentUserDataVM(context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCurrentUser()){
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "getCurrentUserDataVM: получен ответ с данными пользователя ${result.successData}"
                    )
                    state.emit(ProfileState.SuccessLoadProfileData(result.successData.toCurrentUserProfileData()))

                }
                is ApiResult.Error -> {
                    if (result.code == 401) {
                         when (val resultRefreshToken = repository.refreshToken()) {
                             is ApiResult.Success -> {
                                 saveUserDataTokenVM(resultRefreshToken.successData.toUserDataToken())
                                 getCurrentUserDataVM(context)
                             }
                             is ApiResult.Error -> {
                                 showToastToUser(context, result.message)
                             }
                         }

                    } else {
                        showToastToUser(context, result.message)
                    }


                }
            }
        }
    }

}