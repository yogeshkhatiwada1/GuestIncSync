package com.example.guestincsync.ui.my_menu_items

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guestincsync.R

class My_Menu_ItemsFragment : Fragment() {

    companion object {
        fun newInstance() = My_Menu_ItemsFragment()
    }

    private lateinit var viewModel: MyMenuItemsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my__menu__items, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyMenuItemsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}