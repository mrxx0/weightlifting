package com.mrxx0.weightlifting.presentation.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mrxx0.weightlifting.presentation.exercisemodel.create.ExerciseModelCreateScreen
import com.mrxx0.weightlifting.presentation.exercisemodel.main.ExerciseModelMainScreen
import com.mrxx0.weightlifting.presentation.navigation.screens.ScreenContent

fun NavGraphBuilder.exercisesNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.EXERCISES,
        startDestination = ExercisesModelScreen.List.route
    ) {
        composable(route = ExercisesModelScreen.List.route) {
            ExerciseModelMainScreen(navController = navController)
        }
        composable(route = ExercisesModelScreen.Details.route) {
            ScreenContent(name = ExercisesModelScreen.Details.route) {
                navController.popBackStack(
                    route = Graph.EXERCISES,
                    inclusive = false
                )
            }
        }
        composable(route = ExercisesModelScreen.Create.route) {
            ExerciseModelCreateScreen(navController = navController)
        }
    }
}

sealed class ExercisesModelScreen(val route: String) {
    data object List : ExercisesModelScreen(route = "EXERCISESLIST")
    data object Details : ExercisesModelScreen(route = "EXERCISESDETAILS/{exerciseId}") {
        fun createRoute(sessionId: String) = "EXERCISESDETAILS/$sessionId"
    }

    data object Create : ExercisesModelScreen(route = "EXERCISESCREATE")
}