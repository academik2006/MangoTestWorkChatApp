package com.mangotestworkchat.app.ui


import android.annotation.SuppressLint
import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mangotestworkchat.app.navigation.AppNavGraph
import com.mangotestworkchat.app.navigation.NavigationItem
import com.mangotestworkchat.app.navigation.NavigationState
import com.mangotestworkchat.app.navigation.Screen
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.di.ViewModelFactory
import com.mangotestworkchat.app.ui.authorization.AuthorizationScreen
import com.mangotestworkchat.app.ui.chat.ChatScreen
import com.mangotestworkchat.app.ui.profile.ProfileScreen
import com.mangotestworkchat.app.ui.registration.RegistrationScreen
import com.mangotestworkchat.app.ui.theme.red_APP


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppScreen() {
    val snackBarHostState = SnackbarHostState()
    val navController = rememberNavController()
    val navigationState = remember {
        NavigationState(navController)
    }

    val navBackStackEntry = navigationState.navHostController.currentBackStackEntryAsState()
    val currentRout = navBackStackEntry.value?.destination?.route

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {

            var selectedItem by remember { mutableIntStateOf(0) }

            if (isShowBarNavigation(currentRout))
            {

                val items = listOf(NavigationItem.Chat, NavigationItem.Profile)
                NavigationBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
                {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                navigationState.navigateTo(item.screen)
                            },
                            icon = {
                                Icon(
                                    imageVector = ImageVector.vectorResource(item.icon),
                                    contentDescription = null,
                                )
                            }
                        )
                    }
                }
            }
        })
    {
        AppNavGraph(
            navHostController = navigationState.navHostController,
            authorizationScreen = {
                AuthorizationScreen(navigationState)
            },
            chatScreen = {
                ChatScreen(navigationState = navigationState)
            },
            profileScreen = {
                ProfileScreen(navigationState = navigationState)
            },
            registrationScreen = { phone: String ->
                RegistrationScreen(navigationState = navigationState, phone = phone)
            }

        )
    }
}

fun isShowBarNavigation (currentRout: String?) : Boolean {
    return currentRout != Screen.AuthorizationScreen.route
            && currentRout != Screen.RegistrationScreen.route
}