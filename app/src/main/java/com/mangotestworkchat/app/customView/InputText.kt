package com.mangotestworkchat.app.customView

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.mangotestworkchat.app.ui.theme.BgRegularRoboto14
import com.mangotestworkchat.app.utils.MaskTransformation

@Composable
fun InputText(
    viewModifier: Modifier,
    textFieldValue: TextFieldValue,
    keyboardType: KeyboardType,
    hint: String,
    enabled: Boolean,
    placeHolderText: String,
    label: String,
    transformation: VisualTransformation,
    iconId: Int,
    focusRequester: FocusRequester,
    onTextChanged: (text: TextFieldValue) -> Unit

) {
    val focusManager = LocalFocusManager.current

    TextField(
        value = textFieldValue,
        onValueChange = {
            onTextChanged.invoke(it)
        },
        enabled = enabled,
        placeholder = {
            Text(text = placeHolderText, style = BgRegularRoboto14)
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(ImageVector.vectorResource(iconId), contentDescription = "Localized description")
        },
        visualTransformation = transformation
    )

}