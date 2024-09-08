package com.mangotestworkchat.app.ui.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.ui.authorization.ButtonWithIcon
import com.mangotestworkchat.app.ui.authorization.EnterSmsCodeTextField
import com.mangotestworkchat.app.ui.theme.BgRegularRoboto14
import com.mangotestworkchat.app.ui.theme.blue_APP
import com.mangotestworkchat.navigation.NavigationState


@Composable
fun RegistrationScreen(navigationState: NavigationState, phone: String) {
    val component = getApplicationComponent()
    val viewModel: RegistrationViewModel = viewModel(factory = component.getViewModelFactory())

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
        androidx.compose.material.Text(
            text = stringResource(R.string.version_app),
            style = BgRegularRoboto14
        )
        PhoneNumberText(
            maskText = phone,
        )
        EnterSmsCodeTextField()
        ButtonWithIcon("Регистрация")
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















