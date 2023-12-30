package com.mrxx0.weightlifting.presentation.set

import android.annotation.SuppressLint
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
    var exerciseName by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }
    val viewModel = hiltViewModel<SessionViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current


    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    val set = SetEntity(
                        exerciseId = exerciseId,
                        restTime = 200,
                        repetitions = 4,
                        weight = 134,
                        clone = 2
                    )
                    viewModel.createSet(set)
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
                        RepetitionsPicker()
                        Spacer(modifier = Modifier.height(20.dp))
                        WeightPicker()
                        Spacer(modifier = Modifier.height(20.dp))
                        RestTimePicker()
                        Spacer(modifier = Modifier.height(20.dp))
                        MultiplyPicker()
                    }
                }
            }
        }
    }
}

@Composable
fun RepetitionsPicker() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp),
    ) {
        Text("Repetitions: ")
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
fun RestTimePicker() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp),
    ) {
        Text("Rest time: ")
    }
}

@Composable
fun MultiplyPicker() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .padding(10.dp),
    ) {
        Text("Multiply: ")
    }
}