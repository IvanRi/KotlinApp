package com.example.francsapp.utils

import java.text.SimpleDateFormat
import java.util.*

abstract class DateUtils {
    companion object {
        fun obtenerFechaConFormato(formato: String?, zonaHoraria: String?): String? {
            val calendar: Calendar = Calendar.getInstance()
            val date: Date = calendar.getTime()
            val sdf: SimpleDateFormat
            sdf = SimpleDateFormat(formato)
            sdf.setTimeZone(TimeZone.getTimeZone(zonaHoraria))
            return sdf.format(date)
        }
    }
}