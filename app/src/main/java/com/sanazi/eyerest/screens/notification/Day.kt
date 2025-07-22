package com.sanazi.eyerest.screens.notification

import androidx.annotation.StringRes

data class Day(
    @StringRes val stringRes: Int,
    val isSelected: Boolean = false,
    val timeList: List<Time> = listOf()
)