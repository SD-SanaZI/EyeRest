package com.sanazi.eyerest.managers.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.sanazi.eyerest.NotificationBroadcastReceiver
import com.sanazi.eyerest.screens.notification.Time
import java.util.Calendar

class AlarmNotificationManager {
    companion object{
        const val NOTIFICATION_MESSAGE_KEY = "NOTIFICATION_MESSAGE_KEY"
    }

    fun addNotification(
        context: Context,
        message: String,
        time: Time,
        dayOfWeek: Int
    ) {
        val calendar: Calendar = prepareCalendar(time, dayOfWeek)
        val broadcastId = (calendar.timeInMillis / 1000).toInt()
        val pendingIntent = preparePendingIntent(context, message, broadcastId)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC,
            calendar.timeInMillis,
            pendingIntent
        )
    }

    fun deleteNotification(
        context: Context,
        message: String,
        time: Time,
        dayOfWeek: Int
    ) {
        val calendar: Calendar = prepareCalendar(time, dayOfWeek)
        val broadcastId = (calendar.timeInMillis / 1000).toInt()
        val pendingIntent = preparePendingIntent(context, message, broadcastId)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }

    private fun prepareCalendar(
        time: Time,
        dayOfWeek: Int
    ): Calendar = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_WEEK, dayOfWeek+2)
        set(Calendar.HOUR_OF_DAY, time.hour)
        set(Calendar.MINUTE, time.minute)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
        if (before(Calendar.getInstance())) {
            add(Calendar.DATE, 7)
        }
    }

    private fun preparePendingIntent(
        context: Context,
        message: String,
        broadcastId: Int
    ): PendingIntent {
        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        intent.putExtra(NOTIFICATION_MESSAGE_KEY, message)
        return PendingIntent.getBroadcast(
            context,
            broadcastId,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}