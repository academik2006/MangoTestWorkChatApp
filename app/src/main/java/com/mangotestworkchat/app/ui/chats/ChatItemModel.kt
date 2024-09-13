package com.mangotestworkchat.app.ui.chats

data class ChatItemModel(
    val chatId: Int,
    val icon: Int,
    val title: String,
    val lastMessage: String,
    val lastMessageTime: String
)
