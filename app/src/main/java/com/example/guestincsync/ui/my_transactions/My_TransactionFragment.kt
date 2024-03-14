package com.example.guestincsync.ui.my_transactions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.guestincsync.R

class My_TransactionFragment : Fragment() {

    companion object {
        fun newInstance() = My_TransactionFragment()
    }

    private lateinit var viewModel: MyTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my__transaction, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyTransactionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}