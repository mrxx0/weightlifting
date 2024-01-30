package com.mrxx0.weightlifting.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mrxx0.weightlifting.presentation.session.addexercise.SessionAddExerciseScreen
import com.mrxx0.weightlifting.presentation.session.create.SessionCreateScreen
import com.mrxx0.weightlifting.presentation.session.detailsscreen.SessionDetailsScreen
import com.mrxx0.weightlifting.presentation.session.mainscreen.SessionMainScreen

fun NavGraphBuilder.sessionNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SESSION,
        startDestination = SessionScreen.List.route
    ) {
        composable(route = SessionScreen.List.route) {
            SessionMainScreen(navController = navController)
        }
        composable(
            route = SessionScreen.Details.route,
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

        composable(route = SessionScreen.Create.route) {
            SessionCreateScreen(navController = navController)
        }

        composable(route = SessionScreen.AddExercise.route) {
            SessionAddExerciseScreen(navController = navController)
        }
    }
}

sealed class SessionScreen(val route: String) {
    data object List : SessionScreen(route = "SESSIONLIST")
    data object Details : SessionScreen(route = "SESSIONDETAILS/{sessionId}") {
        fun createRoute(sessionId: String) = "SESSIONDETAILS/$sessionId"
    }

    data object Create : SessionScreen(route = "SESSIONCREATE")
    data object AddExercise : SessionScreen(route = "SESSIONADDEXERCISE")
}