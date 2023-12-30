package com.mrxx0.weightlifting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mrxx0.weightlifting.presentation.exercise.ExerciseCreatorScreen
import com.mrxx0.weightlifting.presentation.exercise.ExerciseDetailsScreen
import com.mrxx0.weightlifting.presentation.session.SessionCreatorScreen
import com.mrxx0.weightlifting.presentation.session.SessionDetailsScreen
import com.mrxx0.weightlifting.presentation.session.SessionMainScreen
import com.mrxx0.weightlifting.ui.theme.WeightliftingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeightliftingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val context = LocalContext.current

                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = stringResource(id = R.string.route_main_screen)
                    ) {
                        composable(context.resources.getString(R.string.route_main_screen)) {
                            SessionMainScreen(navController = navController)
                        }
                        composable(context.resources.getString(R.string.route_session_creator_screen)) {
                            SessionCreatorScreen(navController = navController)
                        }
                        composable(
                            "SessionDetailsScreen/{sessionId}",
                            arguments = listOf(navArgument("sessionId") { type = NavType.IntType })
                        ) {
                            it.arguments?.getInt("sessionId")?.let { it1 ->
                                SessionDetailsScreen(
                                    navController = navController,
                                    it1
                                )
                            }
                        }
                        composable(
                            "ExerciseDetailsScreen/{exerciseId}",
                            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType })
                        ) {
                            it.arguments?.getInt("exerciseId")?.let { it1 ->
                                ExerciseDetailsScreen(
                                    navController = navController,
                                    it1
                                )
                            }
                        }
                        composable(
                            "ExerciseCreatorScreen/{sessionId}",
                            arguments = listOf(navArgument("sessionId") { type = NavType.IntType })
                        ) {
                            it.arguments?.getInt("sessionId")?.let { it1 ->
                                ExerciseCreatorScreen(
                                    navController = navController,
                                    it1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}