package com.mangotestworkchat.app.ui.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.APP_LOG
import com.mangotestworkchat.app.data.CurrentUserProfileData
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.navigation.NavigationState
import java.io.ByteArrayOutputStream
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.customView.ButtonWithIcon
import com.mangotestworkchat.app.network.models.request.Avatar
import com.mangotestworkchat.app.ui.theme.BlackRegularRoboto12

@Composable
fun ProfileScreen(navigationState: NavigationState) {

    val component = getApplicationComponent()
    val viewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())
    val context = LocalContext.current.applicationContext
    val state = viewModel.state.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val cityState = remember { mutableStateOf("") }
    val userPhone = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    val birthdayState = remember { mutableStateOf("") }
    val zodiacSignState = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    val photoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) {
        bitmap.value = it
    }

    val stream = ByteArrayOutputStream()
    bitmap.value?.compress(Bitmap.CompressFormat.JPEG, 50, stream)
    val byteArray = stream.toByteArray()

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(imageUri) {
        if (imageUri != null) {
            if (Build.VERSION.SDK_INT < 28) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            } else {
                val source =
                    imageUri?.let { ImageDecoder.createSource(context.contentResolver, it) }
                bitmap.value = source?.let { ImageDecoder.decodeBitmap(it) }
            }
        }
    }

    val profileData = remember {
        mutableStateOf(CurrentUserProfileData())
    }

    LaunchedEffect(Unit) {
        viewModel.getCurrentUserDataVM(context)
    }

    when (state.value) {
        ProfileState.InitState -> {}
        is ProfileState.SuccessLoadProfileData -> {
            name.value = profileData.value.name
            userName.value = profileData.value.userName
            profileData.value = (state.value as ProfileState.SuccessLoadProfileData).data
            cityState.value = profileData.value.city ?: ""
            birthdayState.value = profileData.value.birthday ?: ""
        }

        ProfileState.ErrorLoadProfileData -> {

        }
    }

    ShowProfileData(
        profileData = profileData,
        scrollState = scrollState,
        launcher = launcher,
        keyboardController = keyboardController,
        focusManager = focusManager,
        cityState = cityState,
        birthdayState = userPhone,
        upgradeUserButtonClick = {
            viewModel.upgradeUserVM(
                context = context,
                name = name.value,
                userName = userName.value,
                birthday = birthdayState.value,
                city = cityState.value,
                vk = "",
                instagram = "",
                status = "",
                avatar = Avatar(
                    "",""
                )
            )
        }
    )
}

@Composable
private fun ShowProfileData(
    profileData: MutableState<CurrentUserProfileData>,
    scrollState: ScrollState,
    launcher: ManagedActivityResultLauncher<String, Uri?>,
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager,
    cityState: MutableState<String>,
    birthdayState: MutableState<String>,
    upgradeUserButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
    )
    {

        val avatarId = if (profileData.value.avatar == null) R.drawable.avatar else R.drawable.sport

        Image(
            painter = painterResource(id = avatarId),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .clickable {
                    launcher.launch("image/*")
                }
        )

        ProfileItemConstTextField(
            value = profileData.value.phone,
            imageVector = Icons.Filled.Phone,
            supportingText = "Номер телефона"
        )

        ProfileItemConstTextField(
            value = profileData.value.userName,
            imageVector = Icons.Filled.Face,
            supportingText = "Имя в системе"
        )

        ProfileItemEditableTextField(
            keyboardController,
            focusManager,
            value = cityState.value,
            onValueChange = {
                cityState.value = it
            },
            imageVector = Icons.Filled.Home,
            enabled = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            supportingText = "Город проживания"

        )

        ProfileItemEditableTextField(
            keyboardController,
            focusManager,
            value = birthdayState.value,
            onValueChange = {
                birthdayState.value = it
            },
            imageVector = Icons.Filled.DateRange,
            enabled = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            supportingText = "Дата рождения"
        )

        ProfileItemConstTextField(
            value = "Водолей",
            imageVector = Icons.Filled.Info,
            supportingText = "Знак зодиака по дате рождения"
        )

        ButtonWithIcon(
            textButton = "Сохранить изменения",
            iconId = R.drawable.save_24px
        )
        {
            upgradeUserButtonClick.invoke()
        }

    }
}

@Composable
fun ProfileItemConstTextField(
    value: String,
    imageVector: ImageVector,
    supportingText: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector,
                contentDescription = ""
            )
        },
        enabled = false,
        singleLine = true,
        maxLines = 1,
        supportingText = {
            Text(text = supportingText, style = BlackRegularRoboto12)
        }
    )
}

@Composable
fun ProfileItemEditableTextField(
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager,
    value: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector,
    enabled: Boolean,
    keyboardOptions: KeyboardOptions,
    supportingText: String
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector,
                contentDescription = ""
            )
        },
        enabled = enabled,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onGo = {
            keyboardController?.hide()
            focusManager.clearFocus()
        }),
        supportingText = {
            Text(text = supportingText, style = BlackRegularRoboto12)
        }
    )
}
