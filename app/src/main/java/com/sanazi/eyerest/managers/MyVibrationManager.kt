package com.sanazi.eyerest.managers

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class MyVibrationManager(context: Context, private val key: String,startDuration: Long) {

    private val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private val sharedPreferences = context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
    var duration = sharedPreferences.getLong(key,startDuration)
        set(value) {
            sharedPreferences.edit().putLong(key,value).apply()
            field = value
        }

    fun vibrate() {
        vibrator.vibrate(
            VibrationEffect.createOneShot(
                duration,
                VibrationEffect.DEFAULT_AMPLITUDE
            )
        )
    }
}