package com.mangotestworkchat.app.ui.chat


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.navigation.NavigationState
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto16
import com.mangotestworkchat.app.ui.theme.light_green_APP
import kotlin.text.Typography.nbsp

@Composable
fun ChatScreen(navigationState: NavigationState) {

   val messageList = remember {
        mutableStateOf(
            mutableListOf(
                "Привет",
                "Привет. Как жизнь, что нового?",
                "Особых новостей нет",
                "А нас с супругой пригласили в гости на следующей неделе",
                "Круто, а к кому?",
                "К Петровым"
            )
        )
    }
    ShowChat(messageList)
}

@Composable
fun ShowChat(messageList: MutableState<MutableList<String>>) {

    val newMessage = remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        messageList.value.forEachIndexed { index, item ->
            if (index % 2 == 0) {
                SendMessage(messageText = item)
            } else {
                ReceivedMessage(messageText = item)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 48.dp, start = 10.dp, end = 10.dp)
            )
            {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(48.dp)
                        ),
                    value = newMessage.value,
                    onValueChange = {
                        newMessage.value = it
                    },
                    placeholder = {
                        androidx.compose.material.Text(
                            text = "Сообщение",
                            style = BgBoldRoboto16
                        )
                    },
                    maxLines = 1,
                    enabled = true,
                    singleLine = true,
                    keyboardActions = KeyboardActions(onDone = {
                        sendMessageClick(keyboardController, focusManager, messageList, newMessage)

                    }),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.smile),
                            contentDescription = "Localized description"
                        )
                    },
                    trailingIcon = {
                        Icon (
                            imageVector = ImageVector.vectorResource(R.drawable.send_24px),
                            contentDescription = "Localized description",
                            modifier = Modifier.clickable {
                                sendMessageClick(keyboardController, focusManager, messageList, newMessage)
                            }
                        )
                    }
                )
            }
        }
    }
}
private fun sendMessageClick(
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager,
    messageList: MutableState<MutableList<String>>,
    newMessage: MutableState<String>
) {
    keyboardController?.hide()
    focusManager.clearFocus()
    messageList.value.add(newMessage.value)
    newMessage.value = ""
}

@Composable
fun SendMessage(messageText: String) {

    Surface(modifier = Modifier.padding(start = 10.dp), shape = RoundedCornerShape(10.dp)) {
        Box(
            modifier = Modifier
                .background(light_green_APP)
                .widthIn(0.dp, 273.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "$messageText $nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp",
                color = Color.Black, textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier
                    .widthIn(0.dp, 273.dp)
                    .padding(bottom = 5.dp, end = 20.dp),
                text = "14:25",
                textAlign = TextAlign.Right,
                fontSize = 10.sp
            )

            Image(
                painter = painterResource(id = R.drawable.done_all_24px),
                contentDescription = "",
                modifier = Modifier
                    .scale(0.5f),
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
fun ReceivedMessage(messageText: String) {

    Surface(modifier = Modifier.padding(start = 80.dp), shape = RoundedCornerShape(10.dp)) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .widthIn(0.dp, 273.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "$messageText $nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp",
                color = Color.Black, textAlign = TextAlign.Left
            )
            Text(
                modifier = Modifier
                    .widthIn(0.dp, 273.dp)
                    .padding(bottom = 5.dp, end = 20.dp),
                text = "15:00",
                textAlign = TextAlign.Right,
                fontSize = 10.sp
            )

            Image(
                painter = painterResource(id = R.drawable.check_24px),
                contentDescription = "",
                modifier = Modifier
                    .scale(0.5f),
                alignment = Alignment.Center
            )
        }
    }
}
