package com.sanazi.eyerest.screens.exercises.exercise3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.Exercise6
import com.sanazi.eyerest.R
import com.sanazi.eyerest.Timeout
import com.sanazi.eyerest.managers.MyVibrationController
import com.sanazi.eyerest.screens.elements.ExerciseDescriptionLarge
import com.sanazi.eyerest.screens.elements.ExerciseNumber
import com.sanazi.eyerest.screens.elements.PauseButton
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.elements.PressButton
import com.sanazi.eyerest.screens.elements.StartButton
import com.sanazi.eyerest.screens.elements.TimerComposable
import com.sanazi.eyerest.screens.elements.Underline
import com.sanazi.eyerest.screens.elements.timer

@Composable
fun Exercise3(
    isAutoNext: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    vibrationController: MyVibrationController = MyVibrationController.getInstance(LocalContext.current)
) {
    val state = remember { mutableStateOf(Exercise3States.PREPARE) }
    val repeatCounter = remember { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val timerState = remember { mutableIntStateOf(Exercise3States.ClOSE.duration) }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PopBackRow(navController, isAutoNext)
        if (!isAutoNext) {
            ExerciseNumber(3, Modifier.padding(top = 26.dp))
            Underline(Modifier.padding(top = 5.dp))
        }
        ExerciseDescriptionLarge(R.string.exercise_3,Modifier.padding(top = 30.dp))
        TimerComposable(timerState,
            Modifier
                .padding(top = 30.dp)
                .weight(1f))
        Box(
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val start = {
                vibrationController.smallVibratorManager.vibrate()
                state.value = Exercise3States.ClOSE
            }
            when(state.value){
                Exercise3States.PREPARE -> {
                    LaunchedEffect(Unit) {
                        timerState.intValue = Exercise3States.ClOSE.duration
                        repeatCounter.intValue = 0
                        if (isAutoNext) start.invoke()
                    }
                    StartButton{
                        start.invoke()
                    }
                }
                Exercise3States.ClOSE -> {
                    LaunchedEffect(Unit) {
                        if(repeatCounter.intValue < 5) {
                            timerState.intValue = state.value.duration
                            scope.timer(timerState)
                            vibrationController.smallVibratorManager.vibrate()
                            state.value = Exercise3States.PAUSE
                        }else{
                            vibrationController.longVibratorManager.vibrate()
                            if (isAutoNext) navController.navigate(Timeout(Exercise6(true)))
                            else navController.popBackStack()
                        }
                    }
                    PressButton()
                }
                Exercise3States.PAUSE -> {
                    LaunchedEffect(Unit) {
                        timerState.intValue = state.value.duration
                        scope.timer(timerState)
                        vibrationController.smallVibratorManager.vibrate()
                        state.value = Exercise3States.ClOSE
                        repeatCounter.intValue++
                    }
                    PauseButton()
                }
            }
        }
    }
}