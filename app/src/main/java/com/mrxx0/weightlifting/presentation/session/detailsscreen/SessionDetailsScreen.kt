package com.mrxx0.weightlifting.presentation.session.detailsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.navigation.BottomNavigationBar
import com.mrxx0.weightlifting.presentation.navigation.graph.SessionScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDetailsScreen(
    navController: NavHostController,
    sessionId: Int
) {
    val viewModel = hiltViewModel<SessionDetailsViewModel>()

    val session by viewModel.sharedSession.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val exerciseList by viewModel.exerciseList.observeAsState()


    LaunchedEffect(true) {
        viewModel.getSessionById(sessionId = sessionId)
//        viewModel.loadExerciseList(sessionId)
    }
    session?.let {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        navController.navigate(SessionScreen.AddExercise.route)
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_exercise)
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = stringResource(id = R.string.add_exercise))
                }
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = { TopBar(title = session?.name!!, scrollBehavior = scrollBehavior) },
            bottomBar = { BottomNavigationBar(navController = navController) }

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
                    exerciseList?.let {
                        if (it.isNotEmpty()) {
                            LazyColumn {
                                items(count = it.size) { index ->
                                    val exercise = it[index]
                                    Text("${exercise.name}")
//                                    ExerciseCard(
//                                        exercise = exercise,
//                                        navController = navController
//                                    )
                                }
                                item { Spacer(modifier = Modifier.padding(50.dp)) }
                            }
                        } else {
                            Text(
                                text = "No exercise",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    } ?: run {
                        Text(
                            text = "No exercise",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    } ?: run {
        CircularProgressIndicator()
    }
}