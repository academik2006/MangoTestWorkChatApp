package com.mangotestworkchat.navigation

private const val AUTH_SCREEN = "auth_screen"
private const val CHAT_SCREEN = "chat_screen"
private const val PROFILE_SCREEN = "profile_screen"
private const val REGISTRATION_SCREEN = "registration_screen"
sealed class Screen(val route: String) {
    data object AuthorizationScreen : Screen(AUTH_SCREEN)
    data object ChatScreen : Screen(CHAT_SCREEN)
    data object ProfileScreen : Screen(PROFILE_SCREEN)
    data object RegistrationScreen : Screen(REGISTRATION_SCREEN)
}
