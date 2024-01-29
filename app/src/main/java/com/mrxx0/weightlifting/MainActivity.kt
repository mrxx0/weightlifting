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
import com.mrxx0.weightlifting.presentation.exercise.create.ExerciseCreateScreen
import com.mrxx0.weightlifting.presentation.exercise.details.ExerciseDetailsScreen
import com.mrxx0.weightlifting.presentation.navigation.graph.RootNavigationGraph
import com.mrxx0.weightlifting.presentation.session.create.SessionCreateScreen
import com.mrxx0.weightlifting.presentation.session.detailsscreen.SessionDetailsScreen
import com.mrxx0.weightlifting.presentation.session.mainscreen.SessionMainScreen
import com.mrxx0.weightlifting.presentation.set.create.SetCreateScreen
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
                    RootNavigationGraph(navController = rememberNavController())
                }
            }
        }
    }
}