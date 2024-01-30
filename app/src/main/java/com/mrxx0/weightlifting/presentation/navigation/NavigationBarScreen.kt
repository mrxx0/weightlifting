package com.mrxx0.weightlifting.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object History : NavigationBarScreen(
        route = "History",
        title = "History",
        icon = Icons.Default.History
    )

    data object Session : NavigationBarScreen(
        route = "Session",
        title = "Session",
        icon = Icons.Default.DirectionsRun
    )

    data object ExercisesModel : NavigationBarScreen(
        route = "ExercisesModel",
        title = "Exercises Model",
        icon = Icons.Default.FitnessCenter
    )
}