package com.mangotestworkchat.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mangotestworkchat.app.di.ViewModelFactory
import com.mangotestworkchat.app.ui.BaseAppScreen
import com.mangotestworkchat.app.ui.theme.MangoTestWorkChatAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MangoTestWorkChatAppTheme {
                BaseAppScreen()
            }
        }
    }
}



