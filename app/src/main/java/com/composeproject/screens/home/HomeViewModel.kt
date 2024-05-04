package com.composeproject.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
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
    }

    fun setEvent(event: HomeViewEvent) {
        when (event) {
            is HomeViewEvent.OnSubmitClicked -> {
                viewModelScope.launch {

                    _effect.send(HomeViewEffect.LoadingDataSuccess)
                }
            }

            is HomeViewEvent.OnValueChanged -> {}
            is HomeViewEvent.OnLoadDataClicked -> {
                viewModelScope.launch {
                    _effect.send(HomeViewEffect.LoadingDataSuccess);
                }
            }
        }
    }
}