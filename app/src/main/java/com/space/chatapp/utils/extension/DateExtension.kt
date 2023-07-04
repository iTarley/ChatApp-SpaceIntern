package com.space.chatapp.utils.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.convertTimeToPattern(): String {
    val georgianLocale = Locale("ka", "GE")
    val dateFormat = SimpleDateFormat("MMM d, HH:mm", georgianLocale)
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    return dateFormat.format(calendar.time)
}
fun convertTimeToLong(): Long{
    return System.currentTimeMillis()
}