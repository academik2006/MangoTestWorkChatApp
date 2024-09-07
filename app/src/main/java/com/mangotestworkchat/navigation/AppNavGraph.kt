package com.mangotestworkchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mangotestworkchat.navigation.Screen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    authorizationScreen: @Composable () -> Unit,
    registrationScreen: @Composable (phone: String) -> Unit,
    chatScreen: @Composable () -> Unit,
    profileScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.AuthorizationScreen.route
    )
    {

        composable(Screen.AuthorizationScreen.route) {
            authorizationScreen()
        }
        composable(Screen.RegistrationScreen.route) { navBackStackEntry ->
            val userPhone = navBackStackEntry.arguments?.getString("userPhone")
            registrationScreen(userPhone ?: "")
        }
        composable(Screen.ChatScreen.route) {
            chatScreen()
        }
        composable(Screen.ProfileScreen.route) {
            profileScreen()
        }
    }
}