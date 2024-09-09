package com.mangotestworkchat.app.ui.authorization

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto18
import com.mangotestworkchat.app.ui.theme.BgRegularRoboto14
import com.mangotestworkchat.app.ui.theme.blue_APP
import com.mangotestworkchat.app.utils.MaskTransformation
import com.mangotestworkchat.app.navigation.NavigationState
import com.mangotestworkchat.app.navigation.Screen

@Composable
fun AuthorizationScreen(navigationState: NavigationState) {

    val component = getApplicationComponent()
    val viewModel: AuthorizationViewModel = viewModel(factory = component.getViewModelFactory())
    val context = LocalContext.current.applicationContext

    val userPhone = remember {
        mutableStateOf("")
    }

    val isUserExist = remember {
        mutableStateOf(false)
    }

    val currentRegion = "Russia"
    val phoneMaskCountryData = viewModel.findMaskVM(currentRegion)
    val maskTransformation = viewModel.getMaskTransformationVM(phoneMaskCountryData.mask)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),

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
            userPhone = userPhone,
            maskTransformation = maskTransformation,
            maskText = phoneMaskCountryData.mask,
            maxChar = phoneMaskCountryData.maxChar
        ) {
            val userPhoneCurrent = "+79${userPhone.value}"
            viewModel.checkAuthVM(context = context, userPhoneCurrent)
        }

        if (isUserExist.value) {
            EnterSmsCodeTextField()
        }

        Text(
            modifier = Modifier.clickable {
                val userPhoneCurrent = "+79${userPhone.value}"
                navigationState.navigateTo(Screen.RegistrationScreen.getRouteWithArgs(userPhoneCurrent))
            },
            text = "Регистрация нового пользователя",
            style = BgBoldRoboto18
        )

    }
}

@Composable
fun PhoneNumberTextFieldWithMask(
    userPhone: MutableState<String>,
    maskTransformation: MaskTransformation,
    maskText: String,
    maxChar: Int,
    checkAuth: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    TextField(
        value = userPhone.value,
        onValueChange = {
            userPhone.value = it.take(maxChar)
            if (it.length >= maxChar) {
                focusManager.clearFocus(force = true)
                checkAuth.invoke()
            }
        },
        placeholder = {
            Text(text = maskText, style = BgRegularRoboto14)
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        label = {
            Text(text = stringResource(R.string.user_phone_string))
        },
        leadingIcon = {
            Icon(Icons.Filled.Phone, contentDescription = "Localized description")
        },
        visualTransformation = maskTransformation
    )
}

@Composable
fun EnterSmsCodeTextField() {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        value = password,
        onValueChange = {
            password = it
        },
        singleLine = true,
        label = {
            Text("Введите пароль из СМС")
        },
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        ),
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




