package com.mangotestworkchat.app.ui.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.network.models.request.Avatar
import com.mangotestworkchat.app.network.models.request.UpgradeUserBodyDataModel
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.utils.SharedPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: Repository,
    sharedPref: SharedPref
) : ViewModelBase(repository, sharedPref) {

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
                                 saveUserDataTokenVM(context, resultRefreshToken.successData.toUserDataToken())
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
    fun upgradeUserVM(
        context: Context,
        name: String,
        userName: String,
        birthday: String,
        city: String,
        vk: String,
        instagram: String,
        status: String,
        avatar: Avatar
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            val upgradeUserBodyDataModel = UpgradeUserBodyDataModel(
                name,userName,birthday, city, vk, instagram, status, avatar
            )

            when (val result = repository.upgradeCurrentUser(upgradeUserBodyDataModel)){
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "getCurrentUserDataVM: получен ответ на запрос обновления ${result.successData}"
                    )
                    getCurrentUserDataVM(context)
                }
                is ApiResult.Error -> {
                    if (result.code == 401) {
                        when (val resultRefreshToken = repository.refreshToken()) {
                            is ApiResult.Success -> {
                                saveUserDataTokenVM(context, resultRefreshToken.successData.toUserDataToken())
                                getCurrentUserDataVM(context)
                                showToastToUser(context, result.message)
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