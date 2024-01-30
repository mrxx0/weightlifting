package com.mrxx0.weightlifting.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mrxx0.weightlifting.presentation.history.mainscreen.HistoryMainScreen
import com.mrxx0.weightlifting.presentation.session.detailsscreen.SessionDetailsScreen

fun NavGraphBuilder.historyNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HISTORY,
        startDestination = HistoryScreen.List.route
    ) {

        composable(route = HistoryScreen.List.route) {
            HistoryMainScreen(navController = navController)
        }
        composable(
            route = HistoryScreen.Details.route,
            arguments = listOf(
                navArgument("sessionId") {
                    type = NavType.IntType
                }
            )
        ) {
            it.arguments?.getInt("sessionId")?.let { it1 ->
                SessionDetailsScreen(
                    navController = navController,
                    it1
                )
            }
        }
    }
}

sealed class HistoryScreen(val route: String) {
    data object List : HistoryScreen(route = "HISTORYLIST")
    data object Details : HistoryScreen(route = "HISTORYDETAILS/{sessionId}") {
        fun createRoute(sessionId: String) = "HISTORYDETAILS/$sessionId"
    }
}