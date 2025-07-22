package com.sanazi.eyerest.screens.exercises.exercise7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.Exercise8
import com.sanazi.eyerest.R
import com.sanazi.eyerest.Timeout
import com.sanazi.eyerest.managers.MyVibrationController
import com.sanazi.eyerest.screens.elements.ExerciseDescriptionLarge
import com.sanazi.eyerest.screens.elements.ExerciseNumber
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.elements.StartButton
import com.sanazi.eyerest.screens.elements.TimerComposable
import com.sanazi.eyerest.screens.elements.Underline
import com.sanazi.eyerest.screens.elements.timer

@Composable
fun Exercise7(
    isAutoNext: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    vibrationController: MyVibrationController = MyVibrationController.getInstance(LocalContext.current)
) {
    val state = remember { mutableStateOf(Exercise7States.PREPARE) }
    val scope = rememberCoroutineScope()
    val timerState = remember { mutableIntStateOf(Exercise7States.STARTED.duration) }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PopBackRow(navController, isAutoNext)
        if (!isAutoNext) {
            ExerciseNumber(7, Modifier.padding(top = 26.dp))
            Underline(Modifier.padding(top = 5.dp))
        }
        ExerciseDescriptionLarge(R.string.exercise_7, Modifier.padding(top = 30.dp))
        TimerComposable(timerState,
            Modifier
                .padding(top = 30.dp)
                .weight(1f))
        Box(
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val startButtonAlfaState = remember { mutableFloatStateOf(1f) }
            val start = {
                vibrationController.smallVibratorManager.vibrate()
                startButtonAlfaState.floatValue = 0f
                state.value = Exercise7States.STARTED
            }
            StartButton(
                Modifier.alpha(startButtonAlfaState.floatValue),
                startButtonAlfaState.floatValue == 1f
            ) {
                start.invoke()
            }
            when (state.value) {
                Exercise7States.PREPARE -> {
                    LaunchedEffect(Unit) {
                        timerState.intValue = Exercise7States.STARTED.duration
                    }
                }

                Exercise7States.STARTED -> {
                    LaunchedEffect(Unit) {
                        scope.timer(timerState)
                        vibrationController.longVibratorManager.vibrate()
                        if (isAutoNext) navController.navigate(Timeout(Exercise8(true)))
                        else navController.popBackStack()
                    }
                }
            }
        }
    }
}