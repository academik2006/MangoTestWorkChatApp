package com.mangotestworkchat.app.di.modules

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.location.LocationManager
import com.mangotestworkchat.app.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class SystemServiceModule {

    @Provides
    @ApplicationScope
    fun provideNotificationManager(application: Application): NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @ApplicationScope
    fun provideLocationManager(application: Application): LocationManager {
        return application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

}