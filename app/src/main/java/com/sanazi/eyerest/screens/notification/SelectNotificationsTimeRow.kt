package com.sanazi.eyerest.screens.notification

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SelectNotificationsTimeRow(
    dayList:  SnapshotStateList<Day>,
    index: Int,
    modifier: Modifier = Modifier,
) {
    val day = dayList[index]
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            stringResource(day.stringRes),
            Modifier
                .padding(end = 25.dp)
                .height(25.dp)
                .width(90.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Row(
            Modifier.fillMaxWidth().horizontalScroll(rememberScrollState(0)),
            horizontalArrangement = Arrangement.Start
        ) {
            day.timeList.forEach { time ->
                TimeButton(
                    time,
                    Modifier
                        .padding(end = 11.dp)
                ){
                    val list = day.timeList.toMutableList()
                    list.remove(time)
                    dayList[index] = day.copy(timeList = list.toList())
                }
            }
            AddTimeButton(dayList,index)
        }
    }
}