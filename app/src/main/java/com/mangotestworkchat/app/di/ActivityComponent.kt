package com.mangotestworkchat.app.di

import com.mangotestworkchat.app.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {
    fun injectActivity (activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create (
            @BindsInstance activity: MainActivity
        ) : ActivityComponent
    }

}