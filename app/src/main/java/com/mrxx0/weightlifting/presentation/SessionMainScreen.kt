package com.mrxx0.weightlifting.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mrxx0.weightlifting.R

@Composable
fun SessionMainScreen() {

    val viewModel = hiltViewModel<SessionViewModel>()
    val listSession by viewModel.sessionList.observeAsState()
    val scroll = rememberScrollState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    // TODO : Navigate to session creator screen
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.add_session))
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(text = stringResource(id = R.string.add_session))
            }
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
                horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = stringResource(id = R.string.your_program),
                    textAlign = TextAlign.Center,
                    style = typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                )
                if (!listSession.isNullOrEmpty()) {
                    for (session in listSession!!) {
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 8.dp)
                                .height(IntrinsicSize.Max)
                                .fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(4.dp),
                            shape = RoundedCornerShape(corner = CornerSize(16.dp))
                        ) {

                            Row(
                                Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(start = 10.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = session.day,
                                    style = typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSecondary
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                        .align(Alignment.CenterVertically)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.exercises) + ": ${session.exercises?.size}",
                                        style = typography.titleMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    FilledTonalButton(
                                        colors = ButtonDefaults.filledTonalButtonColors(),
                                        onClick = {}
                                    ) {
                                        Text(
                                            text = "View Details",
                                            style = typography.bodyMedium,
                                            fontStyle = FontStyle.Italic,
                                            color = MaterialTheme.colorScheme.onSurface,
                                        )
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }


}