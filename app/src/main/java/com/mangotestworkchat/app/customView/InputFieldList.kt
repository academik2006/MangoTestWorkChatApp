package com.mangotestworkchat.app.customView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.utils.PrefixTransformation
import com.mangotestworkchat.app.utils.MaskTransformation

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InputFieldList(
    modifier: Modifier,
    nameTextState: MutableState<TextFieldValue>,
    phoneTextState: MutableState<TextFieldValue>? = null,
    nameFocusRequester: FocusRequester,
    focusRequester: FocusRequester
){

    var textFieldFocusState by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp)) {

        // Имя
        InputText(
            keyboardType = KeyboardType.Text,
            textFieldValue = nameTextState.value,
            focusRequester = nameFocusRequester,
            transformation = PrefixTransformation(""),
            viewModifier = Modifier,
            enabled = false,
            label = "",
            placeHolderText = "",
            iconId = R.drawable.badge_24px,
            hint = stringResource(id = R.string.hint_name),
            onTextChanged = { nameTextState.value = it }
        )

        // Телефон
        if (phoneTextState != null){
            Row(modifier = Modifier.fillMaxWidth()) {

                InputText(
                    keyboardType = KeyboardType.Phone,
                    textFieldValue = phoneTextState.value,
                    focusRequester = nameFocusRequester,
                    transformation = MaskTransformation("+70000"),
                    viewModifier = Modifier
                        .padding(top = 20.dp)
                        .width(168.dp),
                    label = "",
                    enabled = false,
                    placeHolderText = "",
                    iconId = R.drawable.badge_24px,
                    hint = stringResource(id = R.string.hint_name),
                    onTextChanged = {
                        if (it.text.length <= 10) phoneTextState.value = it
                    }
                )
            }
        }
    }
}