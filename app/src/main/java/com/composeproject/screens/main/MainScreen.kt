package com.composeproject.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.composeproject.screens.details.DetailsScreen
import com.composeproject.screens.home.HomeScreen

@Composable
fun MainScreen() {
    val naviHostController = rememberNavController()
    NavHost(startDestination = "/home", navController = naviHostController) {
        composable(
            "/home",
        ) {
            HomeScreen(navHostController = naviHostController)
        }

        composable(
            route = "/details/{content}",
            arguments = listOf(navArgument("content") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navHostController = naviHostController,
                content = backStackEntry.arguments?.getString("content") ?: "No content"
            )

        }
    }
}