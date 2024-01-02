package com.mrxx0.weightlifting.presentation.session.ui.edittitlescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.data.mappers.toSessionEntity
import com.mrxx0.weightlifting.domain.Session
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.session.SessionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionEditTitle(
    navController: NavController,
    sessionId: Int
) {
    val scroll = rememberScrollState()
    val sessionViewModel = hiltViewModel<SessionViewModel>()
    val session by sessionViewModel.session.observeAsState()
    var errorText by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(true) {
        sessionViewModel.getSessionById(sessionId = sessionId)
    }

    if (session != null) {
        var newTitle by remember { mutableStateOf(session!!.day) }
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    onClick = {
                        if (newTitle.isBlank() or newTitle.equals(session!!.day)) {
                            // TODO : Handle error
                        } else {
                            val newSession =
                                Session(
                                    id = session!!.id,
                                    day = newTitle.lowercase()
                                        .replaceFirstChar { it.uppercase() },
                                    exercise = session!!.exercise
                                ).toSessionEntity()
                            sessionViewModel.updateSession(newSession)
                            navController.popBackStack()
                        }
                    },
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = "Update"
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Text(text = "Update")
                }
            },
            topBar = {
                TopBar(
                    title = "Update session",
                    scrollBehavior = scrollBehavior
                )
            }
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scroll)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text(stringResource(id = R.string.day)) },
                        singleLine = true,
                        isError = errorText.isNotEmpty()
                    )
                    Text(
                        text = errorText,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}