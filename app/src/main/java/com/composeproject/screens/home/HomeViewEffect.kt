package com.composeproject.screens.home

sealed class HomeViewEffect {
    data class ClickedSuccessfully(val itemName: String) : HomeViewEffect()
}
