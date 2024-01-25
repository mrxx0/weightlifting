package com.mrxx0.weightlifting.presentation.exercise.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.domain.model.Exercise
import com.mrxx0.weightlifting.presentation.exercise.details.ExerciseDetailsViewModel

@Composable
fun ExerciseCard(
    exercise: Exercise,
    navController: NavController
) {
    val viewModel = hiltViewModel<ExerciseDetailsViewModel>()
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
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Text(
                    text = exercise.name!!,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(2f)
                    .clickable {

                        navController.navigate("ExerciseDetailsScreen/${exercise.id}")

                    }
            ) {
                val totalSet =
                    exercise.set?.let { viewModel.getSetValueFromExercise(it) } ?: 0
                Text(
                    text = "Sets: $totalSet",
                    fontStyle = FontStyle.Italic,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterStart)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}