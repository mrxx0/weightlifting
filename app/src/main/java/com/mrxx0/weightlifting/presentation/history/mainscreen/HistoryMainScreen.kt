package com.mrxx0.weightlifting.presentation.history.mainscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import com.mrxx0.weightlifting.presentation.components.TopBar
import com.mrxx0.weightlifting.presentation.navigation.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryMainScreen(
    navController: NavHostController
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {
//                    navController.navigate(SessionScreen.Create.route)
//                },
//                containerColor = MaterialTheme.colorScheme.primaryContainer,
//                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = "History"
//                )
//            }
//
//        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                modifier = Modifier.background(Color.Blue),
                title = "History",
                onActionClick = null,
                onNavigationIconClick = null
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
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
                Text("History")
            }
        }
    }
}