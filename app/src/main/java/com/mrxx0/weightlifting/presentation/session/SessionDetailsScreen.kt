package com.mrxx0.weightlifting.presentation.session

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
import androidx.navigation.NavController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.presentation.SessionViewModel
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.exercise.ExerciseCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDetailsScreen(
    navController: NavController,
    sessionId: Int
) {
    val viewModel = hiltViewModel<SessionViewModel>()

    val session by viewModel.session.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(true) {
        viewModel.getSessionById(sessionId = sessionId)
    }

    if (session != null) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        // TODO : Go to exercise editor screen
                    },
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
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
            topBar = { TopBar(title = session?.day!!, scrollBehavior = scrollBehavior) }

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
                    // TODO : LazyColumn to display exercises
                    LazyColumn {
                        items(count = session!!.exercises!!.size) { index ->
                            val exercise = session!!.exercises!![index]
                            ExerciseCard(
                                exercise = exercise,
                                navController = navController
                            )
                        }
                        item { Spacer(modifier = Modifier.padding(50.dp)) }
                    }

                }
            }
        }
    }
}