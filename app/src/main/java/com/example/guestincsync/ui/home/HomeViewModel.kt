package com.example.guestincsync.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private val _currencyData = MutableLiveData<String>()
    private val _currencyFormat = MutableLiveData<String>()
    private val _dateFormat = MutableLiveData<String>()
    fun setCurrencyDataFromDialog(data: String) {
        _currencyData.value = data
    }
    fun setCurrencyFormatFromDialog(data: String) {
        _currencyFormat.value = data
    }
    fun setDateFormat(data: String){
        _dateFormat.value=data
    }
    val currencyData: LiveData<String> get() = _currencyData
    val currencyFormat: LiveData<String> get() = _currencyFormat
    val dateFormat: LiveData<String> get() = _dateFormat
}