package com.example.guestincsync.ui.my_rooms

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guestincsync.R

class My_RoomsFragment : Fragment() {

    companion object {
        fun newInstance() = My_RoomsFragment()
    }

    private lateinit var viewModel: MyRoomsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my__rooms, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyRoomsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}