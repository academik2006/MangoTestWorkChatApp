package com.mangotestworkchat.app.navigation

private const val ROUTE_AUTH_SCREEN = "auth_screen"
private const val ROUTE_CHAT_SCREEN = "chat_screen"
private const val ROUTE_PROFILE_SCREEN = "profile_screen"
private const val ROUTE_REGISTRATION_SCREEN = "registration_screen/{$USER_PHONE_KEY}"
sealed class Screen(val route: String) {
    data object AuthorizationScreen : Screen(ROUTE_AUTH_SCREEN)
    data object ChatScreen : Screen(ROUTE_CHAT_SCREEN)
    data object ProfileScreen : Screen(ROUTE_PROFILE_SCREEN)
    data object RegistrationScreen : Screen(ROUTE_REGISTRATION_SCREEN) {

        private const val ROUTE_FOR_ARGS = "registration_screen"
        fun getRouteWithArgs (phone: String) : String {
            return "$ROUTE_FOR_ARGS/${phone}"
        }
    }
}
