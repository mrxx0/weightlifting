package com.mrxx0.weightlifting.presentation.navigation.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mrxx0.weightlifting.presentation.navigation.BottomNavigationBar
import com.mrxx0.weightlifting.presentation.navigation.graph.BottomNavigationGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoryScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        BottomNavigationGraph(navController = navController)
    }
}