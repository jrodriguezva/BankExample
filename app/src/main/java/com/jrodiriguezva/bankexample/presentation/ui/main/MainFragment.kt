package com.jrodiriguezva.bankexample.presentation.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jrodiriguezva.bankexample.databinding.MainFragmentBinding
import com.jrodiriguezva.bankexample.utils.observeLiveData
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    private val adapter = BankTransactionAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bindings = MainFragmentBinding.inflate(inflater, container, false)
        bindings.setLifecycleOwner(this)
        bindings.viewModel = viewModel
        bindings.recyclerView.layoutManager = LinearLayoutManager(activity)
        bindings.recyclerView.adapter = adapter
        return bindings.root
    }

    override fun onResume() {
        super.onResume()
        observeLiveData(viewModel.loadBankTransactionList) { viewModel.addData(it) }
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }
}