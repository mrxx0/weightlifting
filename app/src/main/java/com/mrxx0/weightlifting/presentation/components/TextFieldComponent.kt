package com.mrxx0.weightlifting.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun TextFieldComponent(
    labelValue: String,
    onTextChanged: (String) -> Unit,
    errorStatus: Boolean = false,
    errorMessage: String
) {
    val textValue = remember {
        mutableStateOf("")
    }
    Column {
        OutlinedTextField(
            value = textValue.value,
            onValueChange = { it ->
                textValue.value = it
                onTextChanged(it)
            },
            label = { Text(text = labelValue) },
            singleLine = true,
            isError = errorStatus
        )
        if (errorStatus) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}