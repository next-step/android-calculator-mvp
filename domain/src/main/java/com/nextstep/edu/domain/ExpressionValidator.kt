package com.nextstep.edu.domain

object ExpressionValidator {
    private const val INPUT_VALUE_DELIMITER = " "

    fun split(inputValue: String?): List<Any> {
        return validateNullOrBlank(inputValue).split(INPUT_VALUE_DELIMITER)
    }

    private fun validateNullOrBlank(inputValue: String?): String {
        require(!inputValue.isNullOrBlank()) { "입력 값이 Null 또는 공백입니다." }
        return inputValue
    }
}
