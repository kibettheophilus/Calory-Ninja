package com.theophiluskibet.caloryninja.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theophiluskibet.caloryninja.presentation.screens.CaloryDetailScreen
import com.theophiluskibet.caloryninja.presentation.screens.CaloryScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            CaloryScreen()
        }
        composable("details") {
            CaloryDetailScreen()
        }
    }
}
