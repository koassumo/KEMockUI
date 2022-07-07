package com.example.kemockui.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kemockui.model.repository.CalendarRepository

class MainViewModel : ViewModel() {

    val liveDataCurrentTime = MutableLiveData <String>()


    fun updateCurrentTime () {
        val currentDateTime: String = CalendarRepository.getCurrentDateTime()
        liveDataCurrentTime.postValue(currentDateTime)
    }


}