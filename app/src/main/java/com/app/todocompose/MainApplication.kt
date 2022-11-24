package com.app.todocompose

import android.app.Application
import com.app.todocompose.data.AppContainer
import com.app.todocompose.data.DefaultAppContainer


class MainApplication: Application() {
    lateinit var container: AppContainer

    companion object {
        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        container = DefaultAppContainer()
    }
}