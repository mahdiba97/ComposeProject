package com.composeproject.screens.home

sealed class HomeViewEvent {
    data object OnSubmitClicked : HomeViewEvent()
    data object OnLoadDataClicked : HomeViewEvent()
    data class OnValueChanged(val value: String) : HomeViewEvent()
}
