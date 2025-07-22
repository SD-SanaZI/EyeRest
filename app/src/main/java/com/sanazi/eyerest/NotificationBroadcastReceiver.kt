package com.sanazi.eyerest

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.sanazi.eyerest.managers.notification.AlarmNotificationManager.Companion.NOTIFICATION_MESSAGE_KEY
import com.sanazi.eyerest.managers.notification.EyeFitNotificationManager


const val NOTIFICATION_ID = 249

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when(intent.action){
            else -> {
                val resultIntent = Intent(context, MainActivity::class.java)
                val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
                    addNextIntentWithParentStack(resultIntent)
                    getPendingIntent(0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                }
                EyeFitNotificationManager().createNotificationChannel(context)
                val notification = NotificationCompat.Builder(context, EyeFitNotificationManager.CHANNEL_ID)
                    .setSmallIcon(R.drawable.notification_icon)
                    .setContentText(intent.getStringExtra(NOTIFICATION_MESSAGE_KEY))
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true)
                    .build()

                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.notify(NOTIFICATION_ID, notification)
            }
        }
    }
}