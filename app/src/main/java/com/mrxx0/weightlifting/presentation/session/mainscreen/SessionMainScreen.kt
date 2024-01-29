package com.mrxx0.weightlifting.presentation.session.mainscreen

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.R
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.navigation.graph.Graph
import com.mrxx0.weightlifting.presentation.session.card.SessionCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionMainScreen(
    navController: NavController
) {

    val viewModel = hiltViewModel<SessionsViewModel>()
    val listSession by viewModel.allSessions.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.loadSession()
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
//                    navController.navigate(Graph.HISTORY)
//                    navController.navigate(context.resources.getString(R.string.route_session_creator_screen))
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_session)
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(text = stringResource(id = R.string.add_session))
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.background(Color.Blue),
                title = stringResource(id = R.string.your_program),
                onActionClick = null,
                onNavigationIconClick = null
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
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (listSession != null) {
                    LazyColumn {
                        items(count = listSession!!.size) { index ->
                            val session = listSession!![index]
                            SessionCard(
                                session = session,
                                sessionId = session.id,
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