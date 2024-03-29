package com.mrxx0.weightlifting.presentation.set.create

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
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.presentation.components.IntValueFieldComponent
import com.mrxx0.weightlifting.presentation.components.TopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetCreateScreen(
    navController: NavController,
    exerciseId: Int
) {
    val scroll = rememberScrollState()
    val setViewModel = hiltViewModel<SetCreateViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current
    val allValuesChecked = setViewModel.allValidationsPassed.value

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {

                    if (allValuesChecked) {
                        setViewModel.onEvent(SetCreateUiEvent.SetCreateClicked)
                        if (setViewModel.setCreateUiState.value.setSaved) {
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
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .height(IntrinsicSize.Max)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = context.resources.getString(R.string.set_data),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IntValueFieldComponent(
                        labelValue = stringResource(id = R.string.set_repetitions),
                        onIntChanged = {
                            setViewModel.onEvent(
                                SetCreateUiEvent.RepetitionsChanged(
                                    it,
                                    exerciseId
                                )
                            )
                        },
                        errorStatus = setViewModel.setCreateUiState.value.repetitionsError,
                        errorMessage = stringResource(id = R.string.set_repetitions_error)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            IntValueFieldComponent(
                                labelValue = stringResource(id = R.string.set_time_minutes),
                                onIntChanged = {
                                    setViewModel.onEvent(
                                        SetCreateUiEvent.RestTimeMinutesChanged(
                                            it
                                        )
                                    )
                                },
                                errorStatus = setViewModel.setCreateUiState.value.restTimeMinutesError,
                                errorMessage = stringResource(id = R.string.set_rest_time_minutes_error)
                            )
                        }
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            IntValueFieldComponent(
                                labelValue = stringResource(id = R.string.set_time_seconds),
                                onIntChanged = {
                                    setViewModel.onEvent(
                                        SetCreateUiEvent.RestTimeSecondsChanged(
                                            it
                                        )
                                    )
                                },
                                errorStatus = setViewModel.setCreateUiState.value.restTimeSecondsError,
                                errorMessage = stringResource(id = R.string.set_rest_time_seconds_error)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    IntValueFieldComponent(
                        labelValue = stringResource(id = R.string.set_weight),
                        onIntChanged = {
                            setViewModel.onEvent(
                                SetCreateUiEvent.WeightChanged(
                                    it
                                )
                            )
                        },
                        errorStatus = setViewModel.setCreateUiState.value.weightError,
                        errorMessage = stringResource(id = R.string.set_weight_error)
                    )
                    IntValueFieldComponent(
                        labelValue = stringResource(id = R.string.set_repeat),
                        onIntChanged = {
                            setViewModel.onEvent(
                                SetCreateUiEvent.RepeatChanged(
                                    it
                                )
                            )
                        },
                        errorStatus = setViewModel.setCreateUiState.value.repeatError,
                        errorMessage = stringResource(id = R.string.set_repeat_error),
                        action = ImeAction.Done
                    )
                }

            }
        }
    }
}

@Composable
fun SetDataPicker(
    itemDisplay: String,
    updateValue: (Int) -> Unit,
    icon: ImageVector,
    action: ImeAction? = null
) {
    val context = LocalContext.current
    var value by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
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
                },
                leadingIcon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = ""
                    )
                },
                singleLine = true,
                label = {
                    Text(
                        context.resources.getString(R.string.set_enter_value)
                            .plus(" ") + itemDisplay.lowercase()
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (action != null) {
                        action
                    } else {
                        ImeAction.Next
                    }
                ),
            )
        }
    }
}

@Composable
fun SetRestTimePicker(
    itemDisplaySeconds: String,
    itemDisplayMinutes: String,
    updateSeconds: (Int) -> Unit,
    updateMinutes: (Int) -> Unit,
    action: ImeAction? = null
) {

    val context = LocalContext.current
    var minutes by remember { mutableIntStateOf(0) }
    var seconds by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = minutes.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { newValue ->
                    minutes = newValue.toIntOrNull() ?: 0
                    updateMinutes(minutes)
                },
                singleLine = true,
                label = {
                    Text(
                        context.resources.getString(R.string.set_enter_value)
                            .plus(" ") + itemDisplayMinutes.lowercase()
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (action != null) {
                        action
                    } else {
                        ImeAction.Next
                    }
                ),
            )
            Spacer(Modifier.width(10.dp))
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = seconds.takeIf { it != 0 }?.toString() ?: "",
                onValueChange = { newValue ->
                    seconds = newValue.toIntOrNull() ?: 0
                    updateSeconds(seconds)
                },
                singleLine = true,
                label = {
                    Text(
                        context.resources.getString(R.string.set_enter_value)
                            .plus(" ") + itemDisplaySeconds.lowercase()
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (action != null) {
                        action
                    } else {
                        ImeAction.Next
                    }
                ),
            )
        }
    }
}