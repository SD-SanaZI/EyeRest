package com.sanazi.eyerest.screens.notification.db

import androidx.annotation.StringRes
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sanazi.eyerest.screens.notification.Time

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("dayId") @StringRes val dayStringRes: Int,
    @Embedded val time: Time
)

@Entity(tableName = "days")
data class DayEntity(
    @ColumnInfo("dayId") @PrimaryKey @StringRes val dayStringRes: Int,
    val isSelected: Boolean
)