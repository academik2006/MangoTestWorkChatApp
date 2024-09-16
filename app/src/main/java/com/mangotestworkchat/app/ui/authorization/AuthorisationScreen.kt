package com.mangotestworkchat.app.ui.authorization

import android.util.Log
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.navigation.NavigationState
import com.mangotestworkchat.app.navigation.Screen
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto20
import com.mangotestworkchat.app.ui.theme.BgRegularRoboto14
import com.mangotestworkchat.app.ui.theme.blue_APP

@Composable
fun AuthorizationScreen(navigationState: NavigationState) {

    val component = getApplicationComponent()
    val viewModel: AuthorizationViewModel = viewModel(factory = component.getViewModelFactory())
    val context = LocalContext.current.applicationContext
    val state = viewModel.state.collectAsState()

    val userPhone = remember {
        mutableStateOf("")
    }
    val authCode = remember {
        mutableStateOf("")
    }
    val isUserExist = remember {
        mutableStateOf(false)
    }

    val currentRegion = remember {
        mutableStateOf("Russia")
    }

    val userPhoneCurrent = remember {
        mutableStateOf("")
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    when (state.value) {
        AuthorizationState.InitState -> {
            //реализовать коректную авторизацию по токену из памяти я не успел
            // не работал с библиотекой jwt. Хранить его без шифрования плохая практика :(
            val token = viewModel.getSaveTokenVM(context)
            Log.d(APP_LOG, "AuthorizationScreen: в памяти лежит токен доступа $token")
        }

        AuthorizationState.UserExists -> {
            isUserExist.value = true
        }

        AuthorizationState.UserAbsent -> {
            navigationState.navigateTo(Screen.RegistrationScreen.getRouteWithArgs(userPhoneCurrent.value))
        }

        is AuthorizationState.AuthorizationCorrect -> {
            viewModel.saveUserDataTokenVM(
                context,
                (state.value as AuthorizationState.AuthorizationCorrect).data.toUserDataToken()
            )
            navigationState.navigateTo(Screen.ChatsScreen.route)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    )
    {

        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.chat_24dp),
            tint = blue_APP,
            contentDescription = " "
        )

        Text(
            text = stringResource(R.string.version_app),
            style = BgRegularRoboto14
        )

        PhoneNumberTextFieldWithMask(
            viewModel,
            focusRequester = focusRequester,
            userPhone = userPhone,
            currentRegion = currentRegion
        ) { prefix ->
            userPhoneCurrent.value = "$prefix${userPhone.value}"
            viewModel.sendAuthVM(context = context, userPhoneCurrent.value)
        }

        if (isUserExist.value) {
            EnterSmsCodeTextField(authCode) {
                viewModel.checkAuthCodeVM(context, userPhoneCurrent.value, authCode.value)
            }
        }
    }
}

@Composable
fun PhoneNumberTextFieldWithMask(
    viewModel: AuthorizationViewModel,
    focusRequester: FocusRequester,
    userPhone: MutableState<String>,
    currentRegion: MutableState<String>,
    checkAuth: (prefix: String) -> Unit
) {

    val focusManager = LocalFocusManager.current
    val phoneMaskCountryData = viewModel.findMaskVM(currentRegion.value)
    val maskTransformation = viewModel.getMaskTransformationVM(phoneMaskCountryData.mask)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    )

    {
        CountrySelector(phoneMaskCountryData) { country ->
            currentRegion.value = country
        }

        Spacer(modifier = Modifier.width(10.dp))

        OutlinedTextField(
            modifier = Modifier.focusRequester(focusRequester),
            value = userPhone.value,
            onValueChange = {
                userPhone.value = it.take(phoneMaskCountryData.maxChar)
                if (it.length >= phoneMaskCountryData.maxChar) {
                    focusManager.clearFocus(force = true)
                    checkAuth.invoke(phoneMaskCountryData.prefix)
                }
            },
            leadingIcon = {
                Icon(Icons.Filled.Phone, contentDescription = "Localized description")
            },
            placeholder = {
                Text(text = phoneMaskCountryData.mask, style = BgRegularRoboto14)
            },
            singleLine = true,
            maxLines = 1,
            label = {
                Text(text = stringResource(R.string.user_phone_string))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            visualTransformation = maskTransformation

        )

    }

}

@Composable
fun EnterSmsCodeTextField(
    authCode: MutableState<String>,
    onDone: () -> Unit
) {

    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    TextField(
        value = authCode.value,
        onValueChange = {
            authCode.value = it
        },
        singleLine = true,
        label = {
            Text("Введите пароль из СМС")
        },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        keyboardActions = KeyboardActions(onDone = {
            onDone.invoke()
        }),
        trailingIcon = {
            IconButton(onClick = {
                passwordHidden = !passwordHidden
            })
            {
                val visibilityIcon =
                    if (passwordHidden) R.drawable.visibility_24px else R.drawable.visibility_off_24px
                val description = if (passwordHidden) "Показать пароль" else "Скрыть пароль"
                Icon(
                    imageVector = ImageVector.vectorResource(id = visibilityIcon),
                    contentDescription = description
                )
            }
        }
    )
}

@Composable
fun CountrySelector(
    phoneMaskCountryData: PhoneMaskCountryData,
    dropMenuCountryClick: (country: String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    val countries = listOf("Russia", "Belarus")

    Image(
        painter = painterResource(id = phoneMaskCountryData.icon), contentDescription = "",
        modifier = Modifier
            .size(80.dp, 40.dp)
            .clickable {
                expanded = true
            }

    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        },
        modifier = Modifier.background(Color.White)
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            countries.forEach { country ->
                DropdownMenuItem(
                    text = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = country,
                            style = BgBoldRoboto20,
                            textAlign = TextAlign.Center
                        )
                    },
                    onClick = {
                        dropMenuCountryClick.invoke(country)
                        expanded = false
                    },
                    modifier = Modifier
                        .background(color = Color.White)
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        )

                )
            }
        }
    }

}




