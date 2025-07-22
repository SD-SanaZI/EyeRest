package com.sanazi.eyerest.screens.exercises.exercise4

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sanazi.eyerest.Exercise2
import com.sanazi.eyerest.R
import com.sanazi.eyerest.Timeout
import com.sanazi.eyerest.managers.DotSpeedManager
import com.sanazi.eyerest.managers.MyVibrationController
import com.sanazi.eyerest.screens.elements.ExerciseDescription
import com.sanazi.eyerest.screens.elements.ExerciseNumber
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.elements.StartButton
import com.sanazi.eyerest.screens.elements.Underline
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Exercise4(
    isAutoNext: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: Exercise4ViewModel = viewModel(),
    vibrationController: MyVibrationController = MyVibrationController.getInstance(LocalContext.current),
    dotSpeedManager: DotSpeedManager = DotSpeedManager.getInstance(LocalContext.current)
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val scope = rememberCoroutineScope()
    val animateOffsetX: Dp by animateDpAsState(
        targetValue = offsetX.dp, label = "",
        animationSpec = tween((viewModel.state.value.duration * 1000 / dotSpeedManager.speed).toInt())
    ) {
        when (viewModel.state.value) {
            Exercise4States.PREPARE -> {}
            Exercise4States.AFTER_RIGHT -> viewModel.state.value = Exercise4States.UP
            else -> viewModel.state.value =
                Exercise4States.entries[viewModel.state.value.ordinal + 1]
        }
    }
    val animateOffsetY: Dp by animateDpAsState(
        targetValue = offsetY.dp, label = "",
        animationSpec = tween((viewModel.state.value.duration * 1000 / dotSpeedManager.speed).toInt())
    ) {
        when (viewModel.state.value) {
            Exercise4States.PREPARE -> {}
            Exercise4States.AFTER_RIGHT -> viewModel.state.value = Exercise4States.UP
            else -> viewModel.state.value =
                Exercise4States.entries[viewModel.state.value.ordinal + 1]
        }
    }
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
                if (viewModel.state.value == Exercise4States.PREPARE) 1f
                else 0f
            ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isAutoNext) {
                ExerciseNumber(4, Modifier.padding(top = 26.dp))
                Underline(Modifier.padding(top = 5.dp))
            }
            ExerciseDescription(R.string.exercise_4, Modifier.padding(top = 30.dp))
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
            val startButtonAlfaState = remember { mutableFloatStateOf(1f) }
            val start = {
                println("start")
                scope.launch {
                    delay(Exercise4States.PREPARE.duration * 1000L)
                    vibrationController.longVibratorManager.vibrate()
                    if (isAutoNext) navController.navigate(Timeout(Exercise2(true)))
                    else navController.popBackStack()
                }
                vibrationController.smallVibratorManager.vibrate()
                startButtonAlfaState.floatValue = 0f
                viewModel.state.value = Exercise4States.UP
            }
            StartButton(
                Modifier.alpha(startButtonAlfaState.floatValue),
                startButtonAlfaState.floatValue == 1f
            ) {
                start.invoke()
            }
            when (viewModel.state.value) {
                Exercise4States.PREPARE -> {
                    LaunchedEffect(Unit) { if (isAutoNext) start.invoke() }
                }

                Exercise4States.UP -> LaunchedEffect(Unit) {
                    offsetY = -100f
                    offsetX = 0f
                }

                Exercise4States.AFTER_UP -> LaunchedEffect(Unit) {
                    offsetY = 0f
                    offsetX = 0f
                }

                Exercise4States.DOWN -> LaunchedEffect(Unit) {
                    offsetY = 100f
                    offsetX = 0f
                }

                Exercise4States.AFTER_DOWN -> LaunchedEffect(Unit) {
                    offsetY = 0f
                    offsetX = 0f
                }

                Exercise4States.LEFT -> LaunchedEffect(Unit) {
                    offsetY = 0f
                    offsetX = -100f
                }

                Exercise4States.AFTER_LEFT -> LaunchedEffect(Unit) {
                    offsetY = 0f
                    offsetX = 0f
                }

                Exercise4States.RIGHT -> LaunchedEffect(Unit) {
                    offsetY = 0f
                    offsetX = 100f
                }

                Exercise4States.AFTER_RIGHT -> LaunchedEffect(Unit) {
                    offsetY = 0f
                    offsetX = 0f
                }
            }
        }
    }
}

class Exercise4ViewModel : ViewModel() {
    val state = mutableStateOf(Exercise4States.PREPARE)
}