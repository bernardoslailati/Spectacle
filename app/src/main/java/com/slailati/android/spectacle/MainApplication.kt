package com.slailati.android.spectacle

import android.app.Application
import com.slailati.android.spectacle.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApplication)
            modules(
                listOf(
                    dataModule,
                    databaseModule,
                    firebaseModule,
                    networkModule,
                    dataSourceModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }

}