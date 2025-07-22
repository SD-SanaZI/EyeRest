package com.sanazi.eyerest.screens.elements

import androidx.compose.runtime.MutableIntState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun CoroutineScope.timer(timerState: MutableIntState){
    while (timerState.intValue > 0) {
        launch {
            delay(1000)
            timerState.intValue--
        }.join()
    }
}