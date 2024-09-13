package com.mangotestworkchat.app.navigation

import com.mangotestworkchat.app.R

sealed class NavigationItem(
    val screen: String,
    val icon: Int
) {
    data object Chat : NavigationItem(
        screen = Screen.ChatsScreen.route,
        icon = R.drawable.chat_24dp
    )
    data object Profile : NavigationItem(
        screen = Screen.ProfileScreen.route,
        icon = R.drawable.person_24dp
    )
}