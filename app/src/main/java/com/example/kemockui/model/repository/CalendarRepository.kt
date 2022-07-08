package com.example.kemockui.model.repository

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

object CalendarRepository {


    fun getCurrentDateTime(): Calendar {
        var calendar: Calendar = Calendar.getInstance()
        return calendar
    }

    fun getCurrentDateTimeString(): String {
        var calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, 3) //time +3 for Moscow
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return simpleDateFormat.format(calendar.time)
    }

    fun getExamDateTime(): Calendar {
        var calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, 3) //time +3 for Moscow
        calendar.add(Calendar.DAY_OF_MONTH, 5)
        return calendar
    }

    fun getExamDateTimeString(): String {
        var calendar: Calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, 3) //time +3 for Moscow
        calendar.add(Calendar.DAY_OF_MONTH, 5)
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd 10:00:00")
        return simpleDateFormat.format(calendar.time)
    }

    // not HH, mm, ss
    fun compareDates(startDateString: String, endDateString: String): Period {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val startDate: LocalDate = LocalDate.parse(startDateString, formatter)
        val endDate: LocalDate = LocalDate.parse(endDateString, formatter)
        val period: Period = Period.between(startDate, endDate);
        return period
    }

    fun compareDatesTime(startDateString: String, endDateString: String): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val startDate: Date? = simpleDateFormat.parse(startDateString)
        val endDate: Date? = simpleDateFormat.parse(endDateString)

        var different: Long = endDate!!.getTime() - startDate!!.getTime()

        //1 minute = 60 seconds
        //1 hour = 60 x 60 = 3600
        //1 day = 3600 x 24 = 86400
        //milliseconds

        val secondsInMilli : Long = 1000
        val minutesInMilli:Long = secondsInMilli * 60
        val hoursInMilli:Long = minutesInMilli * 60
        val daysInMilli:Long = hoursInMilli * 24

        val elapsedDays:Long = different / daysInMilli
        val elapsedDaysString: String = if (elapsedDays <10) "0$elapsedDays" else "$elapsedDays"
        different = different % daysInMilli

        val elapsedHours:Long = different / hoursInMilli
        val elapsedHoursString: String = if (elapsedHours <10) "0$elapsedHours" else "$elapsedHours"
        different = different % hoursInMilli

        val elapsedMinutes:Long = different / minutesInMilli
        val elapsedMinutesString: String = if (elapsedMinutes <10) "0$elapsedMinutes" else "$elapsedMinutes"
        different = different % minutesInMilli

        val elapsedSeconds:Long = different / secondsInMilli
        val elapsedSecondsString: String = if (elapsedSeconds <10) "0$elapsedSeconds" else "$elapsedSeconds"

        return "0000.00.$elapsedDaysString $elapsedHoursString:$elapsedMinutesString:$elapsedSecondsString"
    }

}