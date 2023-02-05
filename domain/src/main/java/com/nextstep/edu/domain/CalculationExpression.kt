package com.nextstep.edu.domain

class CalculationExpression {
    fun split(inputValue: String): List<String> {
        return inputValue.split(INPUT_VALUE_DELIMITER)
    }

    companion object {
        private const val INPUT_VALUE_DELIMITER = " "
    }
}