package org.examle.simplylaundry.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.examle.simplylaundry.screens.AboutScreen
import org.examle.simplylaundry.screens.HistoryScreen
import org.examle.simplylaundry.screens.MainScreen

@Composable
fun SetupNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Home.route){
        composable(route = Screen.Home.route){
            MainScreen(navController, onNavigationToHistory = {text ->
                navController.navigate("${Screen.History.route}/$text")
            })
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
        composable(route = "${Screen.History.route}/{text}"){ backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            HistoryScreen(navController, historyList = listOf(text))
        }
    }
}


