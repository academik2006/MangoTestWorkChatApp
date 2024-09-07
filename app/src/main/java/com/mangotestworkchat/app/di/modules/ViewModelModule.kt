package com.mangotestworkchat.app.di.modules

import androidx.lifecycle.ViewModel
import com.mangotestworkchat.app.ViewModelBase
import com.mangotestworkchat.app.di.scope.ViewModelKey
import com.mangotestworkchat.app.ui.authorization.AuthorizationViewModel
import com.mangotestworkchat.app.ui.chat.ChatViewModel
import com.mangotestworkchat.app.ui.profile.ProfileViewModel
import com.mangotestworkchat.app.ui.registration.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindAuthorizationViewModel(viewModel: AuthorizationViewModel) : ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    fun bindChatViewModel(viewModel: ChatViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelBase::class)
    fun bindViewModelBase(viewModel: ViewModelBase) : ViewModel
}