package com.nextstep.edu.domain

class CalculationExpression {
    fun split(inputValue: String?): List<String> {
        if (inputValue.isNullOrBlank()) {
            throw IllegalArgumentException("Input Value is Null Or Blank")
        }
        return inputValue.split(VALUE_DIVIDER)
    }

    fun validate(splitInputValue: List<String>) {
        splitInputValue.forEach {
            if (it.isBlank()) {
                throw IllegalArgumentException("Input Value is Blank")
            }
        }
    }

    companion object {
        private const val VALUE_DIVIDER = " "
    }
}