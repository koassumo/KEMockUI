package com.example.kemockui.model.repository

import java.text.SimpleDateFormat
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

    @JvmName("getCurrentDateTime1")
    fun getCurrentDateTime(): String {
        calendar.add(Calendar.HOUR_OF_DAY, 3) //time +3 for Moscow
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
        return simpleDateFormat.format(calendar.time)
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