package com.mrxx0.weightlifting.data.rules

object Validator {

    fun validateNameField(name: String): ValidatorResult {
        if (name.isNotBlank()) {
            return ValidatorResult(false)
        }
        return ValidatorResult(true)
    }

    fun validateIntFieldSubEqualZero(value: Int): ValidatorResult {
        if (value <= 0) {
            return ValidatorResult(false)
        }
        return ValidatorResult(true)
    }

    fun validateIntFieldSubZero(value: Int): ValidatorResult {
        if (value < 0) {
            return ValidatorResult(false)
        }
        return ValidatorResult(true)
    }
}

data class ValidatorResult(
    val status: Boolean = false
)
