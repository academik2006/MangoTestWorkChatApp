package com.mangotestworkchat.app.ui.chat


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.navigation.NavigationState
import com.mangotestworkchat.app.ui.theme.light_green_APP
import kotlin.text.Typography.nbsp

@Composable
fun ChatScreen (navigationState: NavigationState) {

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
        SendMessage("Привет")
        ReceivedMessage("Привет. Как жизнь, что нового?")
        SendMessage("Особых новостей нет")
        ReceivedMessage("А нас с супругой пригласили в гости на следующей неделе")
        SendMessage("Круто, а к кому?")
        ReceivedMessage("К Петровым")
    }
}

@Composable
fun SendMessage(messageText: String){

    Surface(modifier = Modifier.padding(start = 10.dp), shape = RoundedCornerShape(10.dp)) {
        Box(
            modifier = Modifier
                .background(light_green_APP)
                .widthIn(0.dp, 273.dp),
            contentAlignment = Alignment.BottomEnd) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "$messageText $nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp",
                color = Color.Black, textAlign = TextAlign.Left)
            Text(
                modifier = Modifier
                    .widthIn(0.dp, 273.dp)
                    .padding(bottom = 5.dp, end = 20.dp),
                text = "14:25",
                textAlign = TextAlign.Right,
                fontSize = 10.sp)

            Image(
                painter = painterResource(id = R.drawable.done_all_24px),
                contentDescription = "",
                modifier = Modifier
                    .scale(0.5f),
                alignment = Alignment.Center)
        }
    }
}

@Composable
fun ReceivedMessage(messageText: String){

    Surface(modifier = Modifier.padding(start = 80.dp), shape = RoundedCornerShape(10.dp)) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .widthIn(0.dp, 273.dp),
            contentAlignment = Alignment.BottomEnd) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "$messageText $nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp$nbsp",
                color = Color.Black, textAlign = TextAlign.Left)
            Text(
                modifier = Modifier
                    .widthIn(0.dp, 273.dp)
                    .padding(bottom = 5.dp, end = 20.dp),
                text = "15:00",
                textAlign = TextAlign.Right,
                fontSize = 10.sp)

            Image(
                painter = painterResource(id = R.drawable.check_24px),
                contentDescription = "",
                modifier = Modifier
                    .scale(0.5f),
                alignment = Alignment.Center)
        }
    }
}