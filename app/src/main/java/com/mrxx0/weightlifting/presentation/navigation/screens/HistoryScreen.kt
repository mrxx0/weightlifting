package com.mrxx0.weightlifting.presentation.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mrxx0.weightlifting.presentation.navigation.NavigationBar
import com.mrxx0.weightlifting.presentation.navigation.graph.BottomNavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoryScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { NavigationBar(navController = navController) }
    ) {
        BottomNavigationGraph(navController = navController)
    }
}