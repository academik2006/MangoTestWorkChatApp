package com.mangotestworkchat.app.di.modules

import com.mangotestworkchat.app.di.scope.ApplicationScope
import com.mangotestworkchat.app.network.INetwork
import com.mangotestworkchat.app.network.INetworkImpl
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface NetworkModule {
    @ApplicationScope
    @Binds
    fun bindNetwork(impl: INetworkImpl): INetwork
}