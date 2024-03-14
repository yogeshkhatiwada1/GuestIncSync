package com.example.guestincsync.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()

    fun setCurrencyDataFromDialog(data: String) {
        _text.value = data
    }
    val text: LiveData<String> get() = _text
}