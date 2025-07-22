package com.sanazi.eyerest.screens.notification

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sanazi.eyerest.R
import com.sanazi.eyerest.managers.notification.AlarmNotificationManager
import com.sanazi.eyerest.managers.notification.EyeFitNotificationManager
import com.sanazi.eyerest.screens.elements.BorderLine
import com.sanazi.eyerest.screens.elements.PopBackRow
import com.sanazi.eyerest.screens.notification.db.NotificationRoomDatabase
import com.sanazi.eyerest.screens.settings.NOTIFICATION_EXECUTE_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun Notification(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val notificationMessage = stringResource(R.string.notification_message)
    val context = LocalContext.current
    val isNotificationExecutable = context
        .getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        .getBoolean(NOTIFICATION_EXECUTE_KEY, true)
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        PopBackRow(navController)
        NotificationInformation(Modifier.padding(top = 57.dp))
        BorderLine(Modifier.padding(vertical = 17.dp))
        val dayList = remember {
            mutableStateOf(
                mutableStateListOf(
                    Day(R.string.monday),
                    Day(R.string.tuesday),
                    Day(R.string.wednesday),
                    Day(R.string.thursday),
                    Day(R.string.friday),
                    Day(R.string.saturday),
                    Day(R.string.sunday)
                )
            )
        }
        LaunchedEffect(Unit) {
            dayList.value = DayManager(NotificationRoomDatabase.getInstance(context)).getDayList()
                .toMutableStateList()
        }
        SelectNotificationsDays(dayList.value)
        BorderLine(Modifier.padding(vertical = 17.dp))
        SelectNotificationsTime(dayList.value, Modifier.weight(1f))
        BorderLine(Modifier.padding(top = 17.dp, bottom = 22.dp))
        SaveButton(Modifier.clickable {
            CoroutineScope(Dispatchers.IO).launch {
                if (EyeFitNotificationManager().checkNotificationPermissions(context)) {
                    DayManager(NotificationRoomDatabase.getInstance(context))
                        .setDayList(
                            dayList.value,
                            onAddDay = { time, dayOfWeek ->
                                if (isNotificationExecutable)
                                    AlarmNotificationManager().addNotification(
                                        context,
                                        notificationMessage,
                                        time,
                                        dayOfWeek
                                    )
                            },
                            onDeleteDay = { time, dayOfWeek ->
                                if (isNotificationExecutable)
                                    AlarmNotificationManager().deleteNotification(
                                        context,
                                        notificationMessage,
                                        time,
                                        dayOfWeek
                                    )
                            },
                        )
                }
            }
        })
    }
}

