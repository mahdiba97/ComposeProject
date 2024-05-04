package com.composeproject.screens.home

sealed class HomeViewEffect {
    data object LoadingDataFailed : HomeViewEffect()
    data object LoadingDataSuccess : HomeViewEffect()
}
