package com.example.todo.commonComponents

import android.app.Application

class App :Application() {
    override fun onCreate() {
        super.onCreate()
        AppContext.initContext(this)
    }
}