package com.mangotestworkchat.app.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mangotestworkchat.navigation.AppNavGraph
import com.mangotestworkchat.navigation.NavigationItem
import com.mangotestworkchat.navigation.NavigationState
import com.mangotestworkchat.navigation.Screen
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.ui.authorization.AuthorizationScreen
import com.mangotestworkchat.app.ui.chat.ChatScreen
import com.mangotestworkchat.app.ui.profile.ProfileScreen
import com.mangotestworkchat.app.ui.theme.red_APP


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppScreen(
) {
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
        topBar = {

            if (currentRout != Screen.AuthorizationScreen.route
            ) {
                TopAppBar(
                    title = {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = ImageVector.vectorResource(id = R.drawable.chat_24dp),
                                contentDescription = " ",
                                tint = red_APP
                            )
                            Text(text = stringResource(id = R.string.app_name))

                            Spacer(modifier = Modifier.width(120.dp))
                        }

                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navigationState.navigateTo(Screen.ChatScreen.route)
                        }) {
                            Icon(Filled.ArrowBack, "backIcon")
                        }
                    }
                )
            }

        },
        bottomBar = {

            if (currentRout != Screen.AuthorizationScreen.route) {

                val items = listOf(NavigationItem.Chat, NavigationItem.Profile)

                NavigationBar {

                    items.forEach { item ->
                        BottomNavigationItem(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.background)
                                .padding(2.dp),
                            selected = currentRout == item.screen,
                            onClick = {},
                            icon = {
                                ButtonNavigationItem(
                                    iconId = item.icon,
                                    onClick = {
                                    navigationState.navigateTo(item.screen)
                                })
                            },
                            selectedContentColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedContentColor = MaterialTheme.colorScheme.onPrimary

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
            }

        )
    }
}