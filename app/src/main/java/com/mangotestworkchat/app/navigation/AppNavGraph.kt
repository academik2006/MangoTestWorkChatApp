package com.mangotestworkchat.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


const val USER_PHONE_KEY = "userPhone"

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    authorizationScreen: @Composable () -> Unit,
    registrationScreen: @Composable (phone: String) -> Unit,
    chatsScreen: @Composable () -> Unit,
    chatScreen: @Composable () -> Unit,
    profileScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ChatsScreen.route
    )
    {

        composable(Screen.AuthorizationScreen.route) {
            authorizationScreen()
        }
        composable(Screen.RegistrationScreen.route) { navBackStackEntry ->
            val userPhone = navBackStackEntry.arguments?.getString(USER_PHONE_KEY)
            registrationScreen(userPhone ?: "")
        }
        composable(Screen.ChatsScreen.route) {
            chatsScreen()
        }
        composable(Screen.ChatScreen.route) {
            chatScreen()
        }
        composable(Screen.ProfileScreen.route) {
            profileScreen()
        }
    }
}