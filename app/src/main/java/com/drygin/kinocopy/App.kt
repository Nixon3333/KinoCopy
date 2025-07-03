package com.drygin.kinocopy

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.drygin.kinocopy.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Drygin Nikita on 02.07.2025.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}