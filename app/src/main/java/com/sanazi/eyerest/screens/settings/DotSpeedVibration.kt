package com.sanazi.eyerest.screens.settings

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanazi.eyerest.managers.DotSpeedManager

@Composable
fun DotSpeedVibration(
    dotSpeedManager: DotSpeedManager = DotSpeedManager.getInstance(LocalContext.current),
    modifier: Modifier = Modifier
){
    Column(
        modifier.fillMaxWidth()
    ) {
        val sliderPosition = remember { mutableFloatStateOf(dotSpeedManager.speed) }
        val minValue = 0.5f
        val mediumValue = 1
        val maxValue = 1.5f
        DecoratingSlider(
            value = sliderPosition.floatValue,
            onValueChange = { sliderPosition.floatValue = it },
            onValueChangeFinished = { dotSpeedManager.speed = sliderPosition.floatValue },
            valueRange = minValue..maxValue,
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
                    "${value}x",
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

