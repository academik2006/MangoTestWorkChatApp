package com.mangotestworkchat.app.ui.chats

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.navigation.NavigationState
import com.mangotestworkchat.app.navigation.Screen
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto16
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto18

@Composable
fun ChatsScreen(navigationState: NavigationState) {

    val component = getApplicationComponent()
    val viewModel: ChatsViewModel = viewModel(factory = component.getViewModelFactory())
    val state = viewModel.state.collectAsState()

    val chatList = remember {
        mutableStateOf(listOf<ChatItemModel>())
    }

    when (state.value) {

        ChatsState.InitState -> {
            viewModel.getChatsList()
        }

        is ChatsState.SuccessLoadChatsList -> {
            chatList.value = (state.value as ChatsState.SuccessLoadChatsList).data
        }

        ChatsState.ErrorLoadChatsList -> {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    )
    {

        Text(
            text = "Список активных чатов",
            style = BgBoldRoboto18,
            color = MaterialTheme.colors.onSecondary,
        )
        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        )
        {
            items(chatList.value)
            { item ->
                ChatItemRW(item) {
                    //можно передать чат ид, сейчас просто открываю один общий для всех экран
                    navigationState.navigateTo(Screen.ChatScreen.route)
                }
            }

        }


    }
}

@Composable
fun ChatItemRW(
    chatItem: ChatItemModel,
    onItemClick: (chatId: Int) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(48.dp).border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .clickable {
                onItemClick.invoke(chatItem.chatId)
            },
        backgroundColor = Color.White,
        elevation = 2.dp,
        shape = RoundedCornerShape(5.dp)
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {

            Image(
                painter = painterResource(id = chatItem.icon),
                contentDescription = "chat avatar",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)

            )
            Text(text = chatItem.title, style = BgBoldRoboto16)
            Text(text = chatItem.lastMessageTime, style = BgBoldRoboto18)
        }
    }

}