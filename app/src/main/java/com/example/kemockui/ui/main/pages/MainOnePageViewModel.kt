package com.example.kemockui.ui.main.pages

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kemockui.model.repository.CalendarRepository

class MainOnePageViewModel : ViewModel() {

    val liveDataCurrentTime = MutableLiveData <String>()


    fun updateCurrentTime () {
        val currentDateTime: String = CalendarRepository.getCurrentDateTime()
        liveDataCurrentTime.postValue(currentDateTime)
    }


}