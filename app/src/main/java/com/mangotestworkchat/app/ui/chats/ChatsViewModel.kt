package com.mangotestworkchat.app.ui.chats

import androidx.lifecycle.viewModelScope
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.ui.authorization.AuthorizationState
import com.mangotestworkchat.app.ui.profile.ProfileState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModelBase(
    repository
) {
    val state = MutableStateFlow<ChatsState>(ChatsState.InitState)
    fun getChatsList() {
        viewModelScope.launch (Dispatchers.IO) {
            val chatList = repository.getChatsList()
            state.emit(ChatsState.SuccessLoadChatsList(chatList))
        }
    }
}
