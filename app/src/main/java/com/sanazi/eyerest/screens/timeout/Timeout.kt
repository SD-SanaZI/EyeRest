package com.sanazi.eyerest.screens.timeout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.NavRoutes
import com.sanazi.eyerest.R
import com.sanazi.eyerest.managers.MyVibrationController
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.elements.TimerComposable
import com.sanazi.eyerest.screens.elements.timer

const val TIMEOUT_DURATION = 5


@Composable
fun Timeout(
    route: NavRoutes,
    navController: NavController,
    modifier: Modifier = Modifier,
    vibrationController: MyVibrationController = MyVibrationController.getInstance(LocalContext.current)
){
    val scope = rememberCoroutineScope()
    val timerState = remember { mutableIntStateOf(TIMEOUT_DURATION) }
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PopBackRow(navController, true)
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                stringResource(R.string.timeout).uppercase(),
                Modifier.height(23.dp),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Visible
            )
            TimerComposable(
                timerState,
                Modifier.padding(top = 40.dp).fillMaxWidth())
        }
        LaunchedEffect(Unit) {
            scope.timer(timerState)
            vibrationController.longVibratorManager.vibrate()
            navController.navigate(route)
        }
    }
}