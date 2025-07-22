package com.sanazi.eyerest.screens.notification

import com.sanazi.eyerest.R
import com.sanazi.eyerest.screens.notification.db.DayEntity
import com.sanazi.eyerest.screens.notification.db.NotificationEntity
import com.sanazi.eyerest.screens.notification.db.NotificationRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DayManager(
    private val db: NotificationRoomDatabase
) {
    companion object {
        val dayListId = listOf(
            R.string.monday,
            R.string.tuesday,
            R.string.wednesday,
            R.string.thursday,
            R.string.friday,
            R.string.saturday,
            R.string.sunday
        )
    }

    suspend fun getDayList(): List<Day> {
        return withContext(Dispatchers.IO) {
            dayListId.map { day ->
                Day(
                    day,
                    db.NotificationDao().getDayIsSelected(day),
                    db.NotificationDao().getNotifications(day)
                        .map { notificationEntity -> notificationEntity.time }
                )
            }
        }
    }

    suspend fun setDayList(
        list: List<Day>,
        onAddDay: (time: Time, dayOfWeek: Int) -> Unit,
        onDeleteDay: (time: Time, dayOfWeek: Int) -> Unit,
    ){
        withContext(Dispatchers.IO) {
            val dao = db.NotificationDao()
            list.forEachIndexed { dayIndex, day ->
                val oldList = dao.getNotifications(day.stringRes)
                    .map { notificationEntity -> notificationEntity.time }
                day.timeList.filter { !oldList.contains(it) }.forEach {
                    dao.addNotification(NotificationEntity(0, day.stringRes, it))
                    onAddDay(it,dayIndex)
                }
                oldList.filter { !day.timeList.contains(it) }.forEach {
                    dao.deleteNotification(day.stringRes, it.hour, it.minute)
                    onDeleteDay(it,dayIndex)
                }
                if (day.isSelected) {
                    dao.addDay(DayEntity(day.stringRes, true))
                    day.timeList.forEach { onAddDay(it,dayIndex) }
                }
                else {
                    dao.deleteDay(DayEntity(day.stringRes, true))
                    day.timeList.forEach { onDeleteDay(it,dayIndex) }
                }
            }
        }
    }

    suspend fun offDayList(
        onDeleteDay: (time: Time, dayOfWeek: Int) -> Unit,
    ){
        withContext(Dispatchers.IO){
            val dao = db.NotificationDao()
            dayListId.forEachIndexed { dayIndex, day ->
                val notificationList = dao.getNotifications(day)
                notificationList.forEach { onDeleteDay(it.time,dayIndex) }
            }
        }
    }

    suspend fun onDayList(
        onAddDay: (time: Time, dayOfWeek: Int) -> Unit,
    ){
        withContext(Dispatchers.IO){
            val dao = db.NotificationDao()
            dayListId.forEachIndexed { dayIndex, day ->
                val notificationList = dao.getNotifications(day)
                notificationList.forEach { onAddDay(it.time,dayIndex) }
            }
        }
    }
}