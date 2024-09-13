package com.mangotestworkchat.app.customView

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun ButtonWithIcon(
    textButton: String,
    iconId: Int? = null,
    onClick: () -> Unit) {
    Button(
        onClick = {
            onClick.invoke()
        },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
    ) {
        if (iconId != null) {
            Icon(
                imageVector = ImageVector.vectorResource(iconId),
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(textButton)
    }
}