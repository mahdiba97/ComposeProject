package com.composeproject.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(HomeViewState())
    val state = _state.asStateFlow()

    private val _effect = Channel<HomeViewEffect>()
    val effect = _effect.receiveAsFlow()


    init {
        initializeData()
    }

    private fun initializeData() {
        _state.update { stateValue ->
            stateValue.copy(
                wearables = listOf(
                    Pair("Shirt", 25.99),
                    Pair("T-shirt", 19.99),
                    Pair("Trousers", 39.99),
                    Pair("Watch", 129.99),
                    Pair("Hat", 14.99),
                    Pair("Scarf", 12.99),
                    Pair("Gloves", 9.99),
                    Pair("Shoes", 79.99),
                    Pair("Socks", 5.99),
                    Pair("Dress", 49.99),
                    Pair("Skirt", 29.99),
                    Pair("Jacket", 59.99),
                    Pair("Coat", 99.99),
                )
            )
        }
    }

    fun setEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnSubmitClicked -> {

            }

            is HomeViewEvent.OnValueChanged -> {
                val newValue = event.value
                _state.update { newState ->
                    newState.copy(inputValue = newValue)
                }
            }

            is HomeViewEvent.OnItemClicked -> {
                val clickIndex = event.index
                viewModelScope.launch {
                    _effect.send(HomeViewEffect.ClickedSuccessfully(_state.value.wearables[clickIndex].first));
                }
            }
        }
    }
}