package com.mrxx0.weightlifting.presentation.exercisemodel.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.exercisemodel.card.ExerciseModelCard
import com.mrxx0.weightlifting.presentation.navigation.BottomNavigationBar
import com.mrxx0.weightlifting.presentation.navigation.graph.ExercisesModelScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseModelMainScreen(
    navController: NavHostController
) {

    val viewModel = hiltViewModel<ExerciseModelMainViewModel>()
    val listExercises by viewModel.allExercises.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(true) {
        viewModel.loadExercises()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ExercisesModelScreen.Create.route)
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Exercise"
                )
            }

        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.background(Color.Blue),
                title = "Your Exercises",
                onActionClick = null,
                onNavigationIconClick = null
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }

    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                listExercises?.let {
                    LazyColumn {
                        items(count = it.size) { index ->
                            val exerciseModel = it[index]
                            ExerciseModelCard(
                                exerciseModel = exerciseModel,
                            )
                        }
                        item { Spacer(modifier = Modifier.padding(50.dp)) }
                    }
                }
            }
        }
    }

}