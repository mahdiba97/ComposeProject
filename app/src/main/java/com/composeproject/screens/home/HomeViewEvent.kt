package com.composeproject.screens.home

sealed class HomeViewEvent {
    data object OnSubmitClicked : HomeViewEvent()
    data class OnItemClicked(val index: Int) : HomeViewEvent()
    data class OnValueChanged(val value: String) : HomeViewEvent()
}
