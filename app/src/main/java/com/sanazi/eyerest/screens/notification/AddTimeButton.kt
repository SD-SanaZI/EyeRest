package com.sanazi.eyerest.screens.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R
import com.sanazi.eyerest.ui.theme.TimePickerTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTimeButton(
    dayList: SnapshotStateList<Day>,
    index: Int,
    modifier: Modifier = Modifier
) {
    val currentTime = Calendar.getInstance()
    val dialogState = remember { mutableStateOf(false) }
    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true,
    )
    if (dialogState.value) {
        TimePickerTheme {
            AdvancedTimePickerDialog(
                "Select Time",
                { dialogState.value = false },
                {
                    dialogState.value = false
                    val list = dayList[index].timeList.toMutableList()
                    val time = Time(timePickerState.hour, timePickerState.minute)
                    list.add(time)
                    dayList[index] = dayList[index].copy(timeList = list.toList())
                }
            ) {
                TimePicker(timePickerState)
            }
        }
    }
    Box(
        modifier
            .height(38.dp)
            .width(57.dp)
            .clip(RoundedCornerShape(7.dp))
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable {
                dialogState.value = true
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.add_time_icon), null,
            Modifier.size(18.dp)
        )
    }
}

