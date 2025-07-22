package com.sanazi.eyerest.screens.settings

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sanazi.eyerest.R
import com.sanazi.eyerest.managers.notification.AlarmNotificationManager
import com.sanazi.eyerest.screens.notification.DayManager
import com.sanazi.eyerest.screens.notification.db.NotificationRoomDatabase
import kotlinx.coroutines.launch

const val sumWeight = 10f
const val minWeight = 0.1f

sealed class RadioButtonState(
    @StringRes val buttonTitle: Int,
    val leftWeight: Float
)
class RadioButtonOnState: RadioButtonState(R.string.on_str,sumWeight - minWeight)
class RadioButtonOffState: RadioButtonState(R.string.off_str, minWeight)

const val NOTIFICATION_EXECUTE_KEY = "NOTIFICATION_EXECUTE_KEY"

@Composable
fun RadioButton(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
    val state: MutableState<RadioButtonState> = remember {
        val isNotificationExecutable = sharedPreferences.getBoolean(NOTIFICATION_EXECUTE_KEY,true)
        mutableStateOf(
            if (isNotificationExecutable) RadioButtonOnState()
            else RadioButtonOffState()
        )
    }
    val dayManager = remember { DayManager(NotificationRoomDatabase.getInstance(context)) }
    val coroutineScope = rememberCoroutineScope()
    val notificationMessage = stringResource(R.string.notification_message)
    Row(
        modifier
            .height(30.dp)
            .width(50.dp)
            .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(6.dp))
            .padding(3.dp)
            .clickable {
                when (state.value) {
                    is RadioButtonOffState -> {
                        coroutineScope.launch {
                            sharedPreferences.edit().putBoolean(NOTIFICATION_EXECUTE_KEY,true).apply()
                            dayManager.onDayList { time, dayOfWeek ->
                                AlarmNotificationManager().addNotification(
                                    context,
                                    notificationMessage,
                                    time,
                                    dayOfWeek
                                )
                            }
                            state.value = RadioButtonOnState()
                        }
                    }

                    is RadioButtonOnState -> {
                        coroutineScope.launch {
                            sharedPreferences.edit().putBoolean(NOTIFICATION_EXECUTE_KEY,false).apply()
                            dayManager.offDayList { time, dayOfWeek ->
                                AlarmNotificationManager().deleteNotification(
                                    context,
                                    notificationMessage,
                                    time,
                                    dayOfWeek
                                )
                            }
                            state.value = RadioButtonOffState()
                        }
                    }
                }
            }
    ) {
        Box(Modifier.weight(state.value.leftWeight))
        Box(
            Modifier
                .size(24.dp)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(6.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(state.value.buttonTitle),
                Modifier.height(12.dp),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
        Box(Modifier.weight(sumWeight - state.value.leftWeight))
    }
}