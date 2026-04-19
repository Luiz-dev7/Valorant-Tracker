package com.luiz.valorantapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.luiz.valorantapi.ui.screen.ProfileScreen
import com.luiz.valorantapi.ui.screen.SearchScreen
import com.luiz.valorantapi.ui.screen.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Search : Screen("search")
    object Profile : Screen("profile/{gameName}/{tagLine}") {
        fun createRoute(gameName: String, tagLine: String) = "profile/$gameName/$tagLine"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(onNavigateToSearch = {
                navController.navigate(Screen.Search.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            })
        }

        composable(Screen.Search.route) {
            SearchScreen(onNavigateToProfile = { gameName, tagLine ->
                navController.navigate(Screen.Profile.createRoute(gameName, tagLine))
            })
        }

        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument("gameName") { type = NavType.StringType },
                navArgument("tagLine") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ProfileScreen(
                gameName = backStackEntry.arguments?.getString("gameName") ?: "",
                tagLine = backStackEntry.arguments?.getString("tagLine") ?: "",
                onBack = { navController.popBackStack() }
            )
        }
    }
}