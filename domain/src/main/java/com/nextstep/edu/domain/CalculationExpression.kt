package com.nextstep.edu.domain


class CalculationExpression(private var value: List<Any> = emptyList()) {
    init {
        if (value.isNotEmpty()) {
            validate(value)
        }
    }

    constructor(inputValue: String?) : this(split(inputValue))

    override fun toString(): String {
        return value.joinToString(" ") {
            if (it is Operator) it.symbol else it.toString()
        }
    }

    private fun validate(splitInputValue: List<Any>) {
        require(splitInputValue.first().toString().toIntOrNull() != null) { "처음 값은 숫자여야 합니다." }
        require(splitInputValue.last().toString().toIntOrNull() != null) { "마지막 값은 숫자여야 합니다." }
    }

    fun getValue(): List<Any> {
        return value.toList()
    }

    fun add(operand: Int) {
        when {
            value.isNullOrEmpty() -> value += "$operand"
            value.last() is Operator -> value += "$operand"
            value.isNotEmpty() -> {
                value = value.dropLast(1) + "${value.last()}$operand"
            }
        }
    }

    fun add(operator: Operator) {
        when {
            !value.isNullOrEmpty() -> {
                value += listOf(operator)
            }
        }
    }

    companion object {
        private const val INPUT_VALUE_DELIMITER = " "
        private fun split(inputValue: String?): List<Any> {
            return validateNullOrBlank(inputValue).split(INPUT_VALUE_DELIMITER)
        }

        private fun validateNullOrBlank(inputValue: String?): String {
            require(!inputValue.isNullOrBlank()) { "입력 값이 Null 또는 공백입니다." }
            return inputValue
        }
    }
}