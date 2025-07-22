package com.sanazi.eyerest.screens.exercises.exercise5

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.Exercise3
import com.sanazi.eyerest.R
import com.sanazi.eyerest.Timeout
import com.sanazi.eyerest.managers.DotSpeedManager
import com.sanazi.eyerest.managers.MyVibrationController
import com.sanazi.eyerest.screens.elements.ExerciseDescription
import com.sanazi.eyerest.screens.elements.ExerciseNumber
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.elements.StartButton
import com.sanazi.eyerest.screens.elements.Underline
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Exercise5(
    isAutoNext: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    vibrationController: MyVibrationController = MyVibrationController.getInstance(LocalContext.current),
    dotSpeedManager: DotSpeedManager = DotSpeedManager.getInstance(LocalContext.current)
) {
    val state = remember { mutableStateOf(Exercise5States.PREPARE) }
    val time = remember { mutableFloatStateOf(0f) }
    val offsetX = sin(time.floatValue)
    val offsetY = -cos(time.floatValue)
    val duration = remember { 30f }
    val revolutionsPerSecond = remember { duration / 2 / 4 }
    val scope = rememberCoroutineScope()
    val animateOffsetX: Dp by animateDpAsState(
        targetValue = (offsetX * radiusExercise5Ratio).dp, label = "",
        animationSpec = spring()
    )
    val animateOffsetY: Dp by animateDpAsState(
        targetValue = (offsetY * radiusExercise5Ratio).dp, label = "",
        animationSpec = spring()
    )
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PopBackRow(navController, isAutoNext)
        Column(
            Modifier.alpha(
                if (state.value == Exercise5States.PREPARE) 1f
                else 0f
            ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isAutoNext) {
                ExerciseNumber(5, Modifier.padding(top = 26.dp))
                Underline(Modifier.padding(top = 5.dp))
            }
            ExerciseDescription(R.string.exercise_5, Modifier.padding(top = 30.dp))
        }
        Box(
            Modifier
                .padding(top = 30.dp)
                .height(317.dp)
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .offset(animateOffsetX, animateOffsetY)
                    .size(22.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
        Box(
            Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            val startButtonAlfaState = remember {  mutableFloatStateOf(1f) }
            val start = {
                scope.launch {
                    val delay = 1000L / 60
                    var count = 60 * (duration / 2).toInt()
                    val timeDelta =
                        2 * PI.toFloat() * delay / 1000 / revolutionsPerSecond * dotSpeedManager.speed
                    while (count > 0) {
                        launch {
                            delay(delay)
                            time.floatValue += timeDelta
                        }.join()
                        count--
                    }
                    count = 60 * (duration / 2).toInt()
                    while (count > 0) {
                        launch {
                            delay(delay)
                            time.floatValue -= timeDelta
                        }.join()
                        count--
                    }
                }
                vibrationController.smallVibratorManager.vibrate()
                startButtonAlfaState.floatValue = 0f
                state.value = Exercise5States.STARTED
            }
            StartButton(
                Modifier.alpha(startButtonAlfaState.floatValue),
                startButtonAlfaState.floatValue == 1f
            ) {
                start.invoke()
            }
            when (state.value) {
                Exercise5States.PREPARE -> {
                    LaunchedEffect(Unit) {
                        time.floatValue = 0f
                        if (isAutoNext) start.invoke()
                    }
                }

                else -> {
                    DisposableEffect(Unit) {
                        onDispose {
                            scope.cancel()
                        }
                    }
                    LaunchedEffect(state.value) {
                        delay(duration.toLong() * 1000L)
                        vibrationController.longVibratorManager.vibrate()
                        if (isAutoNext) navController.navigate(Timeout(Exercise3(true)))
                        else navController.popBackStack()
                    }
                }
            }
        }
    }
}