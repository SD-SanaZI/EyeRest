package com.sanazi.eyerest.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.managers.MyVibrationController
import com.sanazi.eyerest.R

@Composable
fun DurationOfVibrationSettings(
    vibrationController: MyVibrationController = MyVibrationController.getInstance(LocalContext.current),
    modifier: Modifier = Modifier
){
    Column(
        modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            stringResource(R.string.duration_of_vibration),
            Modifier
                .width(180.dp)
                .height(23.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onTertiary
        )
        VibrationSettings(
            vibrationController.smallVibratorManager,
            R.string.in_exercise_vibration,
            100, 150, 200,
            Modifier.padding(top = 30.dp)
        )
        VibrationSettings(
            vibrationController.longVibratorManager,
            R.string.completion_vibration,
            200, 250, 300,
            Modifier.padding(top = 25.dp)
        )
    }
}