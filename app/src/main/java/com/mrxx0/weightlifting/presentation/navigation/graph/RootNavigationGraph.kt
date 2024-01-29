package com.mrxx0.weightlifting.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mrxx0.weightlifting.presentation.navigation.screens.HistoryScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.HISTORY
    ) {
        composable(route = Graph.HISTORY) {
            HistoryScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HISTORY = "history_graph"
    const val TRAINING = "training_graph"
    const val EXERCISES = "exercises_graph"
}