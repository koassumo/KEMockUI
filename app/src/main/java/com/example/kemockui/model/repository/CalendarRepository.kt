package com.example.kemockui.model.repository

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

object CalendarRepository {
    enum class RangeFlag {
        ONE_MONTH, TWO_MONTHS, ONE_YEAR
    }

    var calendar: Calendar = Calendar.getInstance()

    private var endDateYear: Int = calendar.get(Calendar.YEAR)
    private var endDateMonth: Int = calendar.get(Calendar.MONTH) + 1
    private var endDateDayOfMonth: Int = calendar.get(Calendar.DAY_OF_MONTH)
    private var endDateHour: Int = calendar.get(Calendar.HOUR_OF_DAY)
    private var endDateMinutes: Int = calendar.get(Calendar.MINUTE)
    private var endDateSecs: Int = calendar.get(Calendar.SECOND)

    private var startDateYear: Int = endDateYear
    private var startDateMonth: Int = endDateMonth
    private var startDateDayOfMonth: Int = endDateDayOfMonth


    var currentDateTime: String = ""
        get() = "$endDateYear-$endDateMonth-$endDateDayOfMonth-$endDateHour-$endDateMinutes-$endDateSecs"
        private set

    var endDate: String = ""
        get() = "$endDateYear-$endDateMonth-$endDateDayOfMonth"
        private set

    var startDate: String = ""
        get() = "$startDateYear-$startDateMonth-$startDateDayOfMonth"
        private set


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


    fun refreshDates(rangeFlag: RangeFlag) {
        refreshEndDate()
        refreshStartDate(rangeFlag)
    }

    private fun refreshEndDate() {
        endDateYear = calendar.get(Calendar.YEAR)
        endDateMonth = calendar.get(Calendar.MONTH) + 1
        endDateDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun refreshStartDate(rangeFlag: RangeFlag) {
        when (rangeFlag) {
            RangeFlag.ONE_MONTH -> calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
            RangeFlag.TWO_MONTHS -> calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 2)
            RangeFlag.ONE_YEAR -> calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1)
        }

        startDateYear = calendar.get(Calendar.YEAR)
        startDateMonth = calendar.get(Calendar.MONTH) + 1
        startDateDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    }
}