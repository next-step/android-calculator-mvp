package com.nextstep.edu.domain

class CalculationExpression(val value: List<String>) {
    init {
        validate(value)
    }
    constructor(inputValue: String?) : this(split(inputValue))

    private fun validate(splitInputValue: List<String>) {
        require(splitInputValue.first().toIntOrNull() != null) { "처음 값은 숫자여야 합니다." }
        require(splitInputValue.last().toIntOrNull() != null) { "마지막 값은 숫자여야 합니다." }
    }

    companion object {
        private const val INPUT_VALUE_DELIMITER = " "
        private fun split(inputValue: String?): List<String> {
            return validateNullOrBlank(inputValue).split(INPUT_VALUE_DELIMITER)
        }

        private fun validateNullOrBlank(inputValue: String?): String {
            require(!inputValue.isNullOrBlank()) { "입력 값이 Null 또는 공백입니다." }
            return inputValue
        }
    }
}