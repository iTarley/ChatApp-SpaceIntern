package com.space.chatapp.utils.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimeToPattern(): String {
    val calendar = Calendar.getInstance()
    val dayMonthFormat = SimpleDateFormat("dd/MM, HH:mm", Locale.getDefault())
    return dayMonthFormat.format(calendar.time)
}

fun convertTimeToLong(time:String): Long {
    val dayMonthFormat = SimpleDateFormat("dd/MM, HH:mm", Locale.getDefault())
    return dayMonthFormat.parse(time)?.time ?: 0
}