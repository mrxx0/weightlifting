package com.mrxx0.weightlifting.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mrxx0.weightlifting.presentation.exercisemodel.main.ExerciseModelMainScreen
import com.mrxx0.weightlifting.presentation.history.mainscreen.HistoryMainScreen
import com.mrxx0.weightlifting.presentation.navigation.NavigationBarScreen
import com.mrxx0.weightlifting.presentation.session.mainscreen.SessionMainScreen

@Composable
fun BottomNavigationGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = NavigationBarScreen.History.route
    ) {
        composable(route = NavigationBarScreen.History.route) {
            HistoryMainScreen(navController = navController)
        }
        composable(route = NavigationBarScreen.Session.route) {
            SessionMainScreen(navController = navController)
        }
        composable(route = NavigationBarScreen.ExercisesModel.route) {
            ExerciseModelMainScreen(navController = navController)
        }
        historyNavGraph(navController = navController)
        sessionNavGraph(navController = navController)
        exercisesNavGraph(navController = navController)
    }
}
