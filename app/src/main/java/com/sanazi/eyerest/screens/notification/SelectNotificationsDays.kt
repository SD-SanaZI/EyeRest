package com.sanazi.eyerest.screens.notification

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun SelectNotificationsDays(
    dayList: SnapshotStateList<Day>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dayList.forEachIndexed { dayIndex, day ->
            Column(
                Modifier.height(60.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(day.stringRes).take(2),
                    Modifier.height(23.dp),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                IsSelectedButton(
                    day.isSelected,
                    Modifier
                        .clickable {
                            dayList[dayIndex] = day.copy(isSelected = day.isSelected.not())
                        }
                )
            }
        }
    }
}