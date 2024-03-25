package com.example.guestincsync.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    private val _text2 = MutableLiveData<String>()

    fun setCurrencyDataFromDialog(data: String) {
        _text.value = data
    }
    fun setCurrencyFormatFromDialog(data: String) {
        _text2.value = data
    }
    val text: LiveData<String> get() = _text
    val text2: LiveData<String> get() = _text2
}