package com.example.todo.commonComponents

import android.content.Context

object AppContext {
    private lateinit var appContext:Context

    fun initContext(context: Context){
        appContext = context
    }

    fun getAppContext():Context{
        return appContext
    }
}