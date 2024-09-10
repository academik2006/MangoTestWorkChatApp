package com.mangotestworkchat.app.ui.profile

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mangotestworkchat.app.data.CurrentUserProfileData
import com.mangotestworkchat.app.getApplicationComponent
import com.mangotestworkchat.app.navigation.NavigationState
import java.io.ByteArrayOutputStream
import com.mangotestworkchat.app.R
import com.mangotestworkchat.app.ui.theme.BlackRegularRoboto12

@Composable
fun ProfileScreen(navigationState: NavigationState) {

    val component = getApplicationComponent()
    val viewModel: ProfileViewModel = viewModel(factory = component.getViewModelFactory())
    val context = LocalContext.current.applicationContext
    val state = viewModel.state.collectAsState()

    val cityState = remember { mutableStateOf("") }
    val birthdayState = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

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
            profileData.value = (state.value as ProfileState.SuccessLoadProfileData).data
        }

        ProfileState.ErrorLoadProfileData -> {

        }

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically))
    {
        ShowProfile(profileData, focusManager, keyboardController, cityState,birthdayState)
    }

}

@Composable
fun ShowProfile(
    profileData: MutableState<CurrentUserProfileData>,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController?,
    cityState: MutableState<String>,
    birthdayState: MutableState<String>
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
    )
    {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = ""
        )

        ProfileItemTextField(
            value = profileData.value.phone,
            onValueChange = {},
            imageVector = Icons.Filled.Phone,
            enabled = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            supportingText = "Номер телефона"
        )

        ProfileItemTextField(
            value = profileData.value.name,
            onValueChange = {},
            imageVector = Icons.Filled.Face,
            enabled = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            supportingText = "Имя в системе"
        )

        ProfileItemTextField(
            value = profileData.value.city,
            onValueChange = {
                cityState.value = it
            },
            imageVector = Icons.Filled.Home,
            enabled = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            }),
            supportingText = "Город проживания"

        )

        ProfileItemTextField(
            value = profileData.value.birthday,
            onValueChange = {
                birthdayState.value = it
            },
            imageVector = Icons.Filled.DateRange,
            enabled = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
            supportingText = "Дата рождения"
        )

        ProfileItemTextField(
            value = " ",
            onValueChange = {},
            imageVector = Icons.Filled.Info,
            enabled = false,
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            supportingText = "Знак зодиака по дате рождения"
        )
    }
}

@Composable
fun ProfileItemTextField (
    value: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector,
    enabled: Boolean,
    keyboardOptions: KeyboardOptions,
    supportingText: String,
    keyboardActions: KeyboardActions
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
        maxLines = 1,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        supportingText = {
            Text(text = supportingText, style = BlackRegularRoboto12)
        }
    )
}
