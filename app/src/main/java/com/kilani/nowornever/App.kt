package com.kilani.nowornever

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.kilani.nowornever.di.noworneverKoinModule
import org.koin.android.ext.android.startKoin

class App: Application(), LifecycleObserver {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(noworneverKoinModule))
    }
}