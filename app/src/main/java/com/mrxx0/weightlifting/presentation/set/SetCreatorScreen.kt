package com.mrxx0.weightlifting.presentation.set

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.data.local.set.SetEntity
import com.mrxx0.weightlifting.presentation.SessionViewModel
import com.mrxx0.weightlifting.presentation.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetCreatorScreen(
    navController: NavController,
    exerciseId: Int
) {
    val scroll = rememberScrollState()
    val setViewModel = hiltViewModel<SetViewModel>()
    val sessionViewModel = hiltViewModel<SessionViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()


    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    val set = SetEntity(
                        repetitions = setViewModel.repetitions.value,
                        repeat = setViewModel.repeat.value,
                        weight = setViewModel.weight.value,
                        restTime = setViewModel.restTime.value,
                        exerciseId = exerciseId
                    )
                    sessionViewModel.createSet(set)
                    navController.popBackStack()

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
                title = stringResource(id = R.string.new_set),
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
                modifier = Modifier
                    .verticalScroll(scroll)
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        UniversalPicker(
                            itemDisplay = "Repetitions",
                            updateValue = setViewModel::updateRepetitions
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        UniversalPicker(
                            itemDisplay = "RestTime",
                            updateValue = setViewModel::updateRestTime
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        UniversalPicker(
                            itemDisplay = "Weight",
                            updateValue = setViewModel::updateWeight
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        UniversalPicker(
                            itemDisplay = "Repeat",
                            updateValue = setViewModel::updateRepeat
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UniversalPicker(
    itemDisplay: String,
//    viewModel: SetViewModel,
    updateValue: (Int) -> Unit
) {
    var value by remember {
        mutableIntStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(itemDisplay)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                value = value.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { newValue ->
                    value = newValue.toIntOrNull() ?: 0
                    updateValue(value)
//                    viewModelValue.value = value
//                    viewModel.repetitions.value = value
                },
                singleLine = true,
                label = { Text("enter" + itemDisplay) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
        }
    }
}

@Composable
fun RepetitionsPicker(
    viewModel: SetViewModel
) {
    var repetitions by remember {
        mutableIntStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Repetitions")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                value = repetitions.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { newValue ->
                    repetitions = newValue.toIntOrNull() ?: 0
                    viewModel.repetitions.value = repetitions
                },
                singleLine = true,
                label = { Text("enter repetitions") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
        }
    }
}

@Composable
fun WeightPicker() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp),
    ) {
        Text("Weight: ")
    }
}

@Composable
fun RestTimePicker(
    viewModel: SetViewModel
) {

    var restTime by remember {
        mutableIntStateOf(0)
    }

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Rest time")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            OutlinedTextField(
                value = restTime.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { newValue ->
                    restTime = newValue.toIntOrNull() ?: 0
                    viewModel.restTime.value = restTime
                },
                singleLine = true,
                label = { Text("enter restTime") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
        }
    }
}

@Composable
fun RepeatPicker() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp),
    ) {
        Text("Repeat: ")
    }
}