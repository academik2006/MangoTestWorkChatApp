package com.mangotestworkchat.app.ui.chat

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto16
import com.mangotestworkchat.app.navigation.NavigationState

@Composable
fun ChatScreen (navigationState: NavigationState) {
    Text(
        style = BgBoldRoboto16,
        text = "ChatScreen"
    )

}