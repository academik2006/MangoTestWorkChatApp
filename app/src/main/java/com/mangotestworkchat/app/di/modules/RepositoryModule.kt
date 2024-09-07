package com.mangotestworkchat.app.di.modules

import com.mangotestworkchat.app.di.scope.ApplicationScope
import com.mangotestworkchat.app.repository.Repository
import com.mangotestworkchat.app.repository.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @ApplicationScope
    @Binds
    fun bindRepository(impl: RepositoryImpl): Repository
}