package com.mrxx0.weightlifting.presentation.session.addexercise

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
import androidx.navigation.NavHostController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.exercisemodel.main.ExerciseModelMainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionAddExerciseScreen(
    navController: NavHostController
) {

    val viewModel = hiltViewModel<ExerciseModelMainViewModel>()
    val sessionAddExerciseViewModel = hiltViewModel<SessionAddExerciseViewModel>()
    val listExercises by viewModel.allExercises.observeAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(true) {
        viewModel.loadExercises()
    }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_exercise)
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(text = "Add")
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(title = "Add", scrollBehavior = scrollBehavior) },

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
                            AddExerciseCard(
                                exerciseModel = exerciseModel,
                                onCheckedChange = {sessionAddExerciseViewModel.onCheckedExerciseModel(exerciseModel)},
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