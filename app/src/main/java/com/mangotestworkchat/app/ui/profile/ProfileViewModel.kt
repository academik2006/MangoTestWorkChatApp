package com.mangotestworkchat.app.ui.profile

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.network.INetwork
import com.mangotestworkchat.app.network.api.ApiResult
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.ui.authorization.AuthorizationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    repository: Repository,
    private val iNetwork: INetwork
) : ViewModelBase(repository) {

    val state = MutableStateFlow<ProfileState>(ProfileState.InitState)

    fun getCurrentUserDataVM(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = iNetwork.getCurrentUser()){
                is ApiResult.Success -> {
                    Log.d(
                        APP_LOG,
                        "getCurrentUserDataVM: получен ответ с данными пользователя ${result.successData}"
                    )
                    state.emit(ProfileState.SuccessLoadProfileData(result.successData.toCurrentUserProfileData()))

                }
                is ApiResult.Error -> {
                    showToastToUser(context, result.message)
                }
            }
        }
    }

}