package com.example.test10.ui.messenger

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.common.base_fragment.BaseFragment
import com.example.test10.databinding.FragmentMessengerBinding
import com.example.test10.ui.adapter.MessengerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MessengerFragment :
    BaseFragment<FragmentMessengerBinding>(FragmentMessengerBinding::inflate) {

    private val adapter by lazy {
        MessengerAdapter()
    }

    private val viewModel: MessengerViewModel by viewModels()

    override fun start() {
        observer()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        binding.rvMessenger.layoutManager = LinearLayoutManager(requireContext())
        binding.rvMessenger.adapter = adapter
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loadItems()
                viewModel.itemsState.collect { state ->
                    when {
                        state.data.isNotEmpty() -> adapter.setData(state.data)
                        state.message.isNotEmpty() -> Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}