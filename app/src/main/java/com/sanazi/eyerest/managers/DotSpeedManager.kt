package com.sanazi.eyerest.managers

import android.content.Context

class DotSpeedManager private constructor(context: Context){
    companion object {
        private var instance: DotSpeedManager? = null
        private const val DOT_SPEED_KEY = "DOT_SPEED_KEY"
        private const val DOT_SPEED_STANDARD = 1f

        @Synchronized
        fun getInstance(context: Context): DotSpeedManager =
            instance ?: DotSpeedManager(context).also { instance = it }
    }

    private val sharedPreferences = context.getSharedPreferences(context.packageName,
        Context.MODE_PRIVATE
    )
    var speed = sharedPreferences.getFloat(DOT_SPEED_KEY, DOT_SPEED_STANDARD)
        set(value) {
            sharedPreferences.edit().putFloat(DOT_SPEED_KEY,value).apply()
            field = value
        }
}