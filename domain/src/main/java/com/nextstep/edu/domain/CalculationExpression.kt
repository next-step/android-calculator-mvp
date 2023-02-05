package com.nextstep.edu.domain

class CalculationExpression {
    fun split(inputValue: String?): List<String>? {
        require(!inputValue.isNullOrBlank()) { "입력 값이 Null입니다." }
        return inputValue?.split(INPUT_VALUE_DELIMITER)
    }

    fun validate(splitInputValue: List<String>?) {
        splitInputValue?.forEach {
            require(!it.isNullOrBlank()) { "입력 값이 공백입니다." }
        }
    }

    companion object {
        private const val INPUT_VALUE_DELIMITER = " "
    }
}