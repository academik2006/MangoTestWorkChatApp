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
    chatScreen: @Composable () -> Unit,
    profileScreen: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.ChatScreen.route
    )
    {
        composable(Screen.AuthorizationScreen.route) {
            authorizationScreen()
        }
        composable(Screen.ChatScreen.route) {
            chatScreen()
        }
        composable(Screen.ProfileScreen.route) {
            profileScreen()
        }
    }
}