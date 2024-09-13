package com.mangotestworkchat.app.ui.chats


sealed class ChatsState {

    data object InitState : ChatsState()
    data class SuccessLoadChatsList (val data: List<ChatItemModel>) : ChatsState()
    data object ErrorLoadChatsList : ChatsState()
}