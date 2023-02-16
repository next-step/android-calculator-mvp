package com.nextstep.edu.domain


class CalculationExpression(private var value: List<Any> = emptyList()) {
    init {
        if (value.isNotEmpty()) {
            validate(value)
        }
    }

    constructor(inputValue: String?) : this(ExpressionValidator.split(inputValue))

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
            value.isNullOrEmpty() -> value += operand
            value.last() is Operator -> value += operand
            value.isNotEmpty() -> {
                value = value.dropLast(LAST_INPUT_VALUE) + "${value.last()}$operand".toInt()
            }
        }
    }

    fun add(operator: Operator) {
        if (!value.isNullOrEmpty()) {
            value += listOf(operator)
        }
    }

    fun remove() {
        when (val lastValue = value.lastOrNull()) {
            is Operator -> value = value.dropLast(LAST_INPUT_VALUE)
            is Int -> {
                value = value.dropLast(LAST_INPUT_VALUE) + listOfNotNull((lastValue / OPERAND_DELETE_UNIT).takeIf { it != 0 })
            }
        }
    }

    fun resetValueToResult(initValue: Int) {
        value = emptyList()
        value += initValue
    }

    companion object {
        private const val LAST_INPUT_VALUE = 1
        private const val OPERAND_DELETE_UNIT = 10
    }
}