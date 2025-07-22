package com.sanazi.eyerest.managers

import android.content.Context

class MyVibrationController private constructor(context: Context) {
    companion object {
        private var instance: MyVibrationController? = null
        private const val LONG_STANDARD = 250L
        private const val SMALL_STANDARD = 150L
        private const val LONG_RATIO_KEY = "LONG_RATIO_KEY"
        private const val SMALL_RATIO_KEY = "SMALL_RATIO_KEY"

        @Synchronized
        fun getInstance(context: Context): MyVibrationController =
            instance ?: MyVibrationController(context).also { instance = it }
    }
    val longVibratorManager = MyVibrationManager(context, LONG_RATIO_KEY, LONG_STANDARD)
    val smallVibratorManager = MyVibrationManager(context, SMALL_RATIO_KEY, SMALL_STANDARD)
}