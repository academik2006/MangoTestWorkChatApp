package com.mangotestworkchat.app.ui.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.mangotestworkchat.app.ui.theme.BgBoldRoboto16
import com.mangotestworkchat.app.navigation.NavigationState


@Composable
fun ProfileScreen(navigationState: NavigationState) {
    Text(
        style = BgBoldRoboto16,
        text = "ProfileScreen"
    )

}

//        Scaffold(
//        modifier = Modifier
//            .fillMaxSize()
//            .imePadding(),
//        topBar = {
//            Column(modifier = Modifier) {
//                TopBarWithBackArrow(navController = navController)
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .offset(y = (-54).dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//
//                        if (showPlaceholder.value){
//                            Surface(
//                                modifier = Modifier.size(124.dp),
//                                shape = CircleShape,
//                                color = miraYellow,
//                                elevation = 0.dp,
//                            ){}
//                        }
//
//                        Crossfade(targetState = scrollState.value.dp < 111.dp, label = "") {
//                            if (it){
//                                when {
//                                    showNamePic.value -> {
//                                        NameUserPic(
//                                            firstLetter = nameTextState.value.text.take(1),
//                                            secondLetter = lastNameTextState.value.text.take(1),
//                                            size = picSize.value,
//                                            color = getColor(profileData.value?.color ?: "#FFFFFF")
//                                        ) {
//                                            expanded = true
//                                        }
//                                    }
//                                    showRemotePic.value -> {
//                                        ProfileRemotePic(
//                                            image = profileData.value?.image!!,
//                                            picSize = picSize.value
//                                        ){
//                                            expanded = true
//                                        }
//                                    }
//                                    showLocalPic.value -> {
//                                        UserPic(
//                                            showLocalPic = true,
//                                            showRemotePic = false,
//                                            size = picSize.value,
//                                            bitmap = bitmap) {
//                                            expanded = true
//                                        }
//                                    }
//                                }
//                            } else {
//                                Spacer(modifier = Modifier.height(13.dp))
//                            }
//                        }
//
//                        Text(
//                            modifier = Modifier
//                                .padding(top = 8.dp)
//                                .clickable(
//                                    interactionSource = remember { MutableInteractionSource() },
//                                    indication = rememberRipple(),
//                                    onClick = { expanded = true }
//                                ),
//                            text = "Загрузить фото",
//                            style = caption1Blue
//                        )
//
//                        DropdownMenu(
//                            expanded = expanded,
//                            onDismissRequest = { expanded = false }
//                        ) {
//                            DropdownMenuItem(
//                                onClick = {
//                                    launcher.launch("image/*")
//                                }
//                            ) {
//                                Text(text = "Из Галереи")
//                            }
//                            DropdownMenuItem(
//                                onClick = { photoLauncher.launch() }
//                            ) {
//                                Text(text = "Сделать фото")
//                            }
//                            DropdownMenuItem(
//                                onClick = {
//                                    model.removeAvatar()
//                                    expanded = false
//                                }
//                            ) {
//                                Text(text = "Удалить")
//                            }
//                        }
//                    }
//                }
//            }
//        },
//        bottomBar = {
//            Column() {
//                UpdateProfileRequest(
//                    name = nameTextState.value.text,
//                    enabled = saveEnabled.value &&
//                            nameTextState.value.text.isNotEmpty() &&
//                            lastNameTextState.value.text.isNotEmpty(),
//                    lastName = lastNameTextState.value.text,
//                    phone = phoneTextState.value.text,
//                    email = emailTextState.value.text,
//                    model = model,
//                    bitmap = bitmap,
//                    byteArray = byteArray
//                )
//
//                val isKeyboardOpen by keyboardAsState()
//                AnimatedVisibility(visible = isKeyboardOpen == Keyboard.Closed) {
//                    BottomNavigationBar(navController, bottomBarState)
//                }
//            }
//        },
//    )
//    {
//        Column(
//            modifier = Modifier
//                .offset(y = (-56).dp)
//                .background(color = Color.White)
//                .verticalScroll(scrollState)
//        )
//        {
//
//            InputFieldList(
//                modifier = Modifier,
//                nameTextState = nameTextState,
//                lastNameTextState = lastNameTextState,
//                emailTextState = emailTextState,
//                nameFocusRequester = nameFocusRequester,
//                focusRequester = focusRequester
//            )
//
//            Row(
//                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 27.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                BasicTextField(
//                    value = profileData.value?.phone?.drop(1) ?: "",
//                    onValueChange = {},
//                    textStyle = paragraphBlack,
//                    enabled = false,
//                    visualTransformation = MaskVisualTransformation(PHONE_MASK),
//                )
//
//                CustomIconButton(
//                    modifier = Modifier.padding(top = 2.dp, start = 2.dp),
//                    vectorId = R.drawable.edit,
//                    iconSize = 20.dp,
//                    iconTint = miraBlack.copy(alpha = 0.5f)
//                ) {
//                    scope.launch {
//                        sheetVisible.value = true
//                        sheetState.animateTo(ModalBottomSheetValue.Expanded)
//                    }
//                }
//            }
//        }
//    }
//
//    ProfileDialogState(state = sheetState, visible = sheetVisible)
//
//    val modalFocusRequester = remember { FocusRequester() }
//    if (sheetVisible.value){
//        ModalBottomSheetLayout(
//            modifier = Modifier.fillMaxSize(),
//            sheetBackgroundColor = Color.White,
//            sheetContentColor = Color.White,
//            sheetState = sheetState,
//            sheetContent = {
//                ChangePhoneModal(
//                    state = sheetState,
//                    navController = navController,
//                    focusRequester = modalFocusRequester,
//                    bitmap = bitmap,
//                    byteArray = byteArray,
//                    email = emailTextState.value.text,
//                    lastName = lastNameTextState.value.text,
//                    name = nameTextState.value.text,
//                    profileModel = model
//                )
//            }
//        ) {}
//    }
//}
//
//@Composable
//fun ProfileRemotePic(
//    image: String,
//    picSize: Dp = 124.dp,
//    click: () -> Unit,
//){
//    Image(
//        painter = rememberAsyncImagePainter(image),
//        contentDescription = "image_32",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier
//            .size(picSize)
//            .clip(CircleShape)
//            .clickable(
//                interactionSource = remember { MutableInteractionSource() },
//                indication = rememberRipple(),
//                onClick = click
//            )
//    )
//}
//
//@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
//@Composable
//fun UpdateProfileRequest(
//    name: String,
//    lastName: String,
//    phone: String?,
//    email: String?,
//    model: ProfileViewModel,
//    bitmap: MutableState<Bitmap?>,
//    enabled: Boolean,
//    needHideKeyboard: Boolean = false,
//    dialogState: ModalBottomSheetState? = null,
//    controller: SoftwareKeyboardController? = null,
//    byteArray: ByteArray
//){
//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val isSaving = remember { mutableStateOf(false) }
//
//    SaveButton(enabled = enabled, isSaving = isSaving.value) {
//        updateProfile(
//            scope,
//            name,
//            lastName,
//            phone,
//            email,
//            context,
//            byteArray,
//            model,
//            bitmap,
//            isSaving,
//            controller,
//            needHideKeyboard
//        )
//    }
//}
//
//@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
//fun updateProfile(
//    scope: CoroutineScope,
//    name: String,
//    lastName: String,
//    phone: String?,
//    email: String?,
//    context: Context,
//    byteArray: ByteArray,
//    model: ProfileViewModel,
//    bitmap: MutableState<Bitmap?>,
//    isSaving: MutableState<Boolean>,
//    controller: SoftwareKeyboardController? = null,
//    needHideKeyboard: Boolean = false,
//    dialogState: ModalBottomSheetState? = null,
//    changePhone: Boolean = false,
//    navController: NavController? = null
//){
//
//    val requestName = name.toRequestBody("text/plain".toMediaTypeOrNull())
//    val requestSurname = lastName.toRequestBody("text/plain".toMediaTypeOrNull())
//    val requestPhone = phone?.toRequestBody("text/plain".toMediaTypeOrNull())
//    val requestEmail = email?.toRequestBody("text/plain".toMediaTypeOrNull())
//
//    val file = File(context.cacheDir, "userpic")
//    file.createNewFile()
//
//    var fileOutput: FileOutputStream? = null
//
//    try {
//        fileOutput = FileOutputStream(file)
//    } catch (e: FileNotFoundException) {
//        e.printStackTrace()
//    }
//
//    try {
//        fileOutput?.write(byteArray)
//        fileOutput?.flush()
//        fileOutput?.close()
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//
//    val requestImage = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//    val part = MultipartBody.Part.createFormData("image", file.name, requestImage)
//
//    scope.launch {
//        model.updateProfileData(
//            image = if (bitmap.value == null) null else part,
//            name = requestName,
//            surname = requestSurname,
//            requestPhone = requestPhone,
//            email = requestEmail,
//            isSaving = isSaving,
//            context = context,
//            changePhone = changePhone,
//            navController = navController
//        )
//        if (needHideKeyboard){
//            dialogState?.hide()
//            controller?.hide()
//            dialogVisible.value = false
//        }
//    }
//}
//
//













