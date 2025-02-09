package com.phuhm.flashalertdb.utils

object TimeUtils {
    fun formatTime(milliseconds: Long): String {
        val seconds = milliseconds / 1000.0
        return if (milliseconds % 1000 == 0L) {
            "%.1fs".format(seconds)
        } else {
            "%.3fs".format(seconds)
        }
    }
}
