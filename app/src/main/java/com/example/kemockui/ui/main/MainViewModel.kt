package com.example.kemockui.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kemockui.model.repository.CalendarRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val liveDataExamTime = MutableLiveData <String>()
    val liveDataPeriodToExam = MutableLiveData <String>()

    fun updateCurrentTime () {

        val examDateTime: String = CalendarRepository.getExamDateTimeString()
        liveDataExamTime.postValue(examDateTime)

        viewModelScope.launch {
            while(true) {
                delay(1000)
                val currentDateTime: String = CalendarRepository.getCurrentDateTimeString()
                val period: String = CalendarRepository.compareDatesTime(currentDateTime, examDateTime)
                liveDataPeriodToExam.postValue(period)
            }
        }
    }


}