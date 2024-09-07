package com.mangotestworkchat.app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.mangotestworkchat.app.di.ApplicationComponent
import com.mangotestworkchat.app.di.DaggerApplicationComponent
import java.lang.ref.WeakReference

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent () : ApplicationComponent {
    Log.d(APP_LOG, "getApplicationComponent: создан компонент")
    return (LocalContext.current.applicationContext as App).component
}