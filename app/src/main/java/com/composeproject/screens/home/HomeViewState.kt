package com.composeproject.screens.home

data class HomeViewState(
    val wearables :List<Pair<String, Double>> = emptyList(),
    var inputValue: String = "",
    val counter: Int = 0
)
