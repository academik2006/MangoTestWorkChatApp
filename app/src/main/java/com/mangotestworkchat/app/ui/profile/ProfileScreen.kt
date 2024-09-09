package com.mangotestworkchat.app.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mangotestworkchat.app.customView.InputFieldList
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto16
import com.mangotestworkchat.app.navigation.NavigationState


@Composable
fun ProfileScreen(navigationState: NavigationState) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),

        )
    {
        Text(
            style = BgBoldRoboto16,
            text = "ProfileScreen"
        )
        ShowProfile()
    }

}

@Composable
fun ShowProfile () {

    val scrollState = rememberScrollState()

    val nameFocusRequester = remember { FocusRequester() }
    val focusRequester = remember { FocusRequester() }

    val nameTextState = remember { mutableStateOf(TextFieldValue("")) }
    val phoneTextState = remember {
        mutableStateOf(TextFieldValue("", selection = TextRange(25)))
    }

    Column(
        modifier = Modifier
            //.offset(y = (-56).dp)
            .background(color = Color.White)
            .verticalScroll(scrollState)
    )
    {

        InputFieldList(
            modifier = Modifier,
            nameTextState = nameTextState,
            nameFocusRequester = nameFocusRequester,
            focusRequester = focusRequester
        )

        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 27.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = "+79833106634" ?: "",
                onValueChange = {},
                textStyle = BgBoldRoboto16,
                enabled = false,
                visualTransformation = VisualTransformation.None,
            )

        }
    }

}
