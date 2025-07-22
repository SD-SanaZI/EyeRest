package com.sanazi.eyerest.screens.notification.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NotificationDao {
    @Upsert
    fun addDay(day: DayEntity)

    @Query("SELECT isSelected FROM days WHERE dayId = :dayId")
    fun getDayIsSelected(dayId: Int): Boolean

    @Delete
    fun deleteDay(day: DayEntity)

    @Upsert
    fun addNotification(time: NotificationEntity)

    @Query("SELECT * FROM notifications WHERE dayId = :dayId")
    fun getNotifications(dayId: Int): List<NotificationEntity>

    @Query("DELETE FROM notifications WHERE dayId = :dayId AND hour = :hour AND minute = :minute")
    fun deleteNotification(dayId: Int, hour: Int, minute: Int)
}