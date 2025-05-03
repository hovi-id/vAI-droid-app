package com.goodwy.dialer.extensions


import com.goodwy.commons.extensions.getFormattedDuration
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

fun Long.getFormattedDuration(forceShowHours: Boolean = false): String {
    return this.div(1000F).roundToInt().getFormattedDuration(forceShowHours)
}

fun Long.formatDate(): String {
    return SimpleDateFormat("MMM d, yyyy", Locale.getDefault()).format(Date(this))
}
