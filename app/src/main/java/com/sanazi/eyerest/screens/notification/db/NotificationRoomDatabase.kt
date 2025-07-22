package com.sanazi.eyerest.screens.notification.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [(NotificationEntity::class), (DayEntity::class)], version = 1)
abstract class NotificationRoomDatabase : RoomDatabase() {
    abstract fun NotificationDao(): NotificationDao

    companion object {
        private var INSTANCE: NotificationRoomDatabase? = null
        fun getInstance(context: Context): NotificationRoomDatabase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NotificationRoomDatabase::class.java,
                        context.packageName

                    ).fallbackToDestructiveMigration().build()
                    CoroutineScope(Dispatchers.IO).launch {
                    }
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}