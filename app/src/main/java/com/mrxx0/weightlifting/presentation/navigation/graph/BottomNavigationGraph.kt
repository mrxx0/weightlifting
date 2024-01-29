package com.mrxx0.weightlifting.presentation.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.mrxx0.weightlifting.presentation.navigation.NavigationBarScreen
import com.mrxx0.weightlifting.presentation.navigation.screens.ScreenContent
import com.mrxx0.weightlifting.presentation.session.detailsscreen.SessionDetailsScreen
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
            SessionMainScreen(navController = navController)
        }
        composable(route = NavigationBarScreen.Training.route) {
            ScreenContent(
                name = "TRAINING",
                onClick = {
                    navController.navigate(Graph.TRAINING)
                }
            )
        }
        composable(route = NavigationBarScreen.Exercises.route) {
            ScreenContent(
                name = "EXERCISES",
                onClick = {
                    navController.navigate(Graph.EXERCISES)
                }
            )
        }
        historyNavGraph(navController = navController)
        trainingNavGraph(navController = navController)
        exercisesNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.historyNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HISTORY,
        startDestination = HistoryScreen.Details.route
    ) {

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

fun NavGraphBuilder.trainingNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.TRAINING,
        startDestination = TrainingScreen.List.route
    ) {
        composable(route = TrainingScreen.List.route) {
            ScreenContent(name = TrainingScreen.List.route) {
                navController.navigate(TrainingScreen.Details.route)
            }
        }
        composable(route = TrainingScreen.Details.route) {
            ScreenContent(name = TrainingScreen.Details.route) {
                navController.popBackStack(
                    route = Graph.TRAINING,
                    inclusive = false
                )
            }
        }
        composable(route = TrainingScreen.Create.route) {
            ScreenContent(name = TrainingScreen.Create.route) {
                navController.popBackStack(
                    route = Graph.TRAINING,
                    inclusive = false
                )
            }
        }
    }
}

fun NavGraphBuilder.exercisesNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EXERCISES,
        startDestination = ExercisesScreen.List.route
    ) {
        composable(route = ExercisesScreen.List.route) {
            ScreenContent(name = ExercisesScreen.List.route) {
                navController.navigate(ExercisesScreen.Details.route)
            }
        }
        composable(route = ExercisesScreen.Details.route) {
            ScreenContent(name = ExercisesScreen.Details.route) {
                navController.popBackStack(
                    route = Graph.EXERCISES,
                    inclusive = false
                )
            }
        }
        composable(route = ExercisesScreen.Create.route) {
            ScreenContent(name = ExercisesScreen.Create.route) {
                navController.popBackStack(
                    route = Graph.EXERCISES,
                    inclusive = false
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

sealed class TrainingScreen(val route: String) {
    data object List : TrainingScreen(route = "TRAININGLIST")
    data object Details : TrainingScreen(route = "TRAININGDETAILS/{sessionId}") {
        fun createRoute(sessionId: String) = "TRAININGDETAILS/$sessionId"
    }
    data object Create : TrainingScreen(route = "TRAININGCREATE")
}

sealed class ExercisesScreen(val route: String) {
    data object List : ExercisesScreen(route = "EXERCISESLIST")
    data object Details : ExercisesScreen(route = "EXERCISESDETAILS/{exerciseId}") {
        fun createRoute(sessionId: String) = "EXERCISESDETAILS/$sessionId"
    }
    data object Create : ExercisesScreen(route = "EXERCISESCREATE")
}