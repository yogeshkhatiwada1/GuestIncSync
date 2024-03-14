package com.example.guestincsync.ui.my_business_stat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guestincsync.R

class My_Business_StatFragment : Fragment() {

    companion object {
        fun newInstance() = My_Business_StatFragment()
    }

    private lateinit var viewModel: MyBusinessStatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_business_stat, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyBusinessStatViewModel::class.java)
        // TODO: Use the ViewModel
    }

}