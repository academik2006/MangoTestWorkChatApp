package com.mangotestworkchat.app.ui.registration

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.ui.theme.BgRegularRoboto14
import com.mangotestworkchat.app.ui.theme.blue_APP
import com.mangotestworkchat.app.navigation.NavigationState


@Composable
fun RegistrationScreen(navigationState: NavigationState, phone: String) {
    val component = getApplicationComponent()
    val viewModel: RegistrationViewModel = viewModel(factory = component.getViewModelFactory())
    val context = LocalContext.current.applicationContext


    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val name = remember {
        mutableStateOf("")
    }
    val userName = remember {
        mutableStateOf("")
    }

    val isShowRegistrationButton = remember {
        mutableStateOf(false)
    }

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
        PhoneNumberText(
            maskText = phone,
        )
        NameTextField(name = name, focusManager, keyboardController)
        UserNameTextField(userName = userName, focusManager, keyboardController, isShowRegistrationButton)
        if (isShowRegistrationButton.value) {
            ButtonWithIcon("Регистрация") {
                val isDataValid = viewModel.isDataUserValidVM(
                    context = context,
                    name = name.value,
                    userName = userName.value
                )
                Log.d(APP_LOG, "isDataValid: $isDataValid")

            }
        }
    }
}

@Composable
fun PhoneNumberText(
    maskText: String
) {
    var text by rememberSaveable { mutableStateOf("") }

    TextField(
        value = maskText,
        onValueChange = {
            text = it
        },
        enabled = false,
        leadingIcon = {
            Icon(Icons.Filled.Phone, contentDescription = "Localized description")
        }
    )
}

@Composable
fun NameTextField(
    name: MutableState<String>,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?
) {
    TextField(
        value = name.value,
        onValueChange = {
            name.value = it
        },
        placeholder = {
            Text(text = "ФИО", style = BgRegularRoboto14)
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.moveFocus(FocusDirection.Down)
        }),
        label = {
            Text(text = "Имя пользователя")
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.person_24dp),
                contentDescription = "Localized description"
            )
        }
    )
}

@Composable
fun UserNameTextField(
    userName: MutableState<String>,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    isShowRegistrationButton: MutableState<Boolean>
) {

    TextField(
        value = userName.value,
        onValueChange = {
            userName.value = it
        },
        placeholder = {
            Text(text = "exampleNameUser0", style = BgRegularRoboto14)
        },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            focusManager.clearFocus()
            isShowRegistrationButton.value = true
        }),
        label = {
            Text(text = "Имя в системе")
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.badge_24px),
                contentDescription = "Localized description"
            )
        },

        )
}

@Composable
fun ButtonWithIcon(textButton: String, onClick: () -> Unit) {
    Button(
        onClick = {
            onClick.invoke()
        },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.how_to_reg_24px),
            contentDescription = "Localized description",
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(textButton)
    }
}

















