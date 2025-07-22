package com.sanazi.eyerest.screens.settings

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanazi.eyerest.managers.MyVibrationManager

@Composable
fun VibrationSettings(
    vibrationManager: MyVibrationManager,
    @StringRes annotationString: Int,
    minValue: Int,
    mediumValue: Int,
    maxValue: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(annotationString),
                Modifier
                    .width(150.dp)
                    .height(23.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onTertiary
            )
            TryButton{ vibrationManager.vibrate() }
        }
        val sliderPosition = remember { mutableFloatStateOf(vibrationManager.duration.toFloat()) }
        DecoratingSlider(
            value = sliderPosition.floatValue,
            onValueChange = { sliderPosition.floatValue = it },
            onValueChangeFinished = { vibrationManager.duration = sliderPosition.floatValue.toLong() },
            valueRange = minValue.toFloat()..maxValue.toFloat(),
            modifier = Modifier
                .padding(top = 10.dp)
                .padding(10.dp, 1.dp),
        )
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            listOf(minValue, mediumValue, maxValue).forEach { value ->
                Text(
                    "${value}ms",
                    Modifier
                        .width(40.dp)
                        .height(23.dp),
                    fontSize = 10.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}