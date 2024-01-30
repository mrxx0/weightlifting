package com.mrxx0.weightlifting.presentation.session.addexercise

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mrxx0.weightlifting.domain.model.ExerciseModel
import com.mrxx0.weightlifting.presentation.exercisemodel.details.ExerciseDetailsViewModel

@Composable
fun AddExerciseCard(
    exerciseModel: ExerciseModel,
    onCheckedChange: (Boolean) -> Unit,
    navController: NavController
) {
    val viewModel = hiltViewModel<SessionAddExerciseViewModel>()
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
                    .weight(2f)
            ) {
                exerciseModel.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .fillMaxSize()
                    .weight(1f)
                    .clickable {
//                        navController.navigate("ExerciseDetailsScreen/${exercise.id}")
                    }
            ) {
                Checkbox(
                    checked = viewModel.selectedExerciseModel.contains(exerciseModel),
                    onCheckedChange = { isChecked -> onCheckedChange(isChecked) },
                    modifier = Modifier.align(Alignment.Center),
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = MaterialTheme.colorScheme.onPrimary,
                        checkedColor = MaterialTheme.colorScheme.onPrimary,
                        checkmarkColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        }
    }
}