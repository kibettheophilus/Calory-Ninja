package com.theophiluskibet.caloryninja.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theophiluskibet.caloryninja.presentation.screens.CaloryDetailScreen
import com.theophiluskibet.caloryninja.presentation.screens.CaloryScreen
import com.theophiluskibet.caloryninja.presentation.screens.OnboardingScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "onboarding"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("onboarding") {
            OnboardingScreen(onNavigateToHome = { navController.navigate("home") })
        }
        composable("home") {
            CaloryScreen(navController = navController)
        }
        composable("details/{food}") {
            CaloryDetailScreen(it.arguments?.getString("food"))
        }
    }
}
