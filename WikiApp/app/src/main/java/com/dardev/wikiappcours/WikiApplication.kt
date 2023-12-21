package com.dardev.wikiappcours

import android.app.Application
import com.dardev.wikiappcours.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WikiApplication:Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@WikiApplication)
            modules(appModule)
        }
    }
}