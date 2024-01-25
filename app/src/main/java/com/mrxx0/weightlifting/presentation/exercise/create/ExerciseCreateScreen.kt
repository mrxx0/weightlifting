package com.mrxx0.weightlifting.presentation.exercise.create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.presentation.components.TextFieldComponent
import com.mrxx0.weightlifting.presentation.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCreateScreen(
    navController: NavController,
    sessionId: Int
) {
    val scroll = rememberScrollState()
    val viewModel = hiltViewModel<ExerciseCreateViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val nameError = viewModel.exerciseCreateUiState.value.nameError

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    if (!nameError) {
                        viewModel.onEvent(ExerciseCreateUiEvent.ExerciseCreateClicked)
                        if (viewModel.exerciseCreateUiState.value.exerciseSaved) {
                            navController.popBackStack()
                        }
                    }
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = stringResource(id = R.string.create_exercise)
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(text = stringResource(id = R.string.create_exercise))
            }
        },
        topBar = {
            TopBar(
                title = stringResource(id = R.string.new_exercise),
                scrollBehavior = scrollBehavior
            )
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
        ) {
            Column(
                modifier = Modifier.verticalScroll(scroll)
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = stringResource(id = R.string.exercise_name))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        TextFieldComponent(
                            labelValue = stringResource(id = R.string.name),
                            onTextChanged = {
                                viewModel.onEvent(ExerciseCreateUiEvent.NameChanged(it, sessionId))
                            },
                            errorStatus = viewModel.exerciseCreateUiState.value.nameError,
                            errorMessage = stringResource(id = R.string.create_exercise_error_message)
                        )
                    }
                }
            }
        }
    }
}