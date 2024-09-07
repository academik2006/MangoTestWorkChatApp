package com.mangotestworkchat.app.di

import android.app.Application
import com.mangotestworkchat.app.App
import com.mangotestworkchat.app.di.modules.RepositoryModule
import com.mangotestworkchat.app.di.modules.SystemServiceModule
import com.mangotestworkchat.app.di.modules.ViewModelModule
import com.mangotestworkchat.app.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        RepositoryModule::class,
        SystemServiceModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun injectApp(application: App)
    fun createActivityComponent(): ActivityComponent.Factory
    fun getViewModelFactory(): ViewModelFactory

    @Component.Factory
    interface ApplicationComponentBuilder {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}