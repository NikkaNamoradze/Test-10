package com.example.test10.ui.messenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test10.data.response_handler.Resource
import com.example.test10.domain.use_case.GetItemsUseCase
import com.example.test10.ui.model.ItemModelUI
import com.example.test10.ui.toModelUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessengerViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val _itemsState = MutableStateFlow(ViewState())
    val itemsState = _itemsState.asStateFlow()

    fun loadItems() {
        viewModelScope.launch {
            resetViewState()
            getItemsUseCase.invoke().collect {
                when (it) {
                    is Resource.Success -> {
                        _itemsState.value = _itemsState.value.copy(data = it.data!!.map { it.toModelUI() })
                    }
                    is Resource.Failure -> {
                        _itemsState.value = _itemsState.value.copy(
                            data = emptyList(),
                            message = it.message!!
                        )
                    }
                }
            }
        }
    }

    private fun resetViewState() {
        _itemsState.value = _itemsState.value.copy(data = emptyList(), message = "")
    }

    data class ViewState(
        val data: List<ItemModelUI> = emptyList(),
        val message: String = ""
    )

}