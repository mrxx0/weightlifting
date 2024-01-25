package com.mrxx0.weightlifting.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun IntValueFieldComponent(
    labelValue: String,
    onIntChanged: (Int) -> Unit,
    errorStatus: Boolean = false,
    errorMessage: String,
    action: ImeAction? = null
) {
    var value by remember {
        mutableIntStateOf(0)
    }
    Column {
        OutlinedTextField(
            value = value.takeIf { it != 0 }?.toString() ?: "0",
            onValueChange = {
                value = it.toIntOrNull() ?: 0
                onIntChanged(value)
            },
            label = { Text(text = labelValue) },
            singleLine = true,
            isError = errorStatus,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = action ?: ImeAction.Next
            ),
        )
        if (errorStatus) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}