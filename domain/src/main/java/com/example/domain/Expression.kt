package com.example.domain

data class Expression(
    private val values: List<Any> = emptyList()
) {

    /**
    값이 아무 것도 없을 경우, 값이 있을 경우
     */
    fun append(input: Any): Expression {
        return when (input) {
            is Operator -> plus(input)
            is Int -> plus(input)
            else -> throw IllegalArgumentException("해당 값은 수식에 필요한 값이 아닙니다.")
        }
    }

    /**
    들어올 케이스
    [3, +]   ->   1   = [3, +, 1]
    [3]      ->   1   = [31]
    [+]      ->   1   = [1]
     */
    private operator fun plus(operand: Int): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> {
                if (values.size == 1) return Expression(listOf(operand))
                Expression(values + operand)
            }
            is Int -> {
                val nOperand = "${values.last()}${operand}".toInt()
                Expression(values.dropLast(1) + nOperand)
            }
            null -> Expression(listOf(operand))

            else -> throw IllegalArgumentException("피연산자를 추가하는 과정에서 Error 발생")
        }
    }

    /**
    들어올 케이스
    [3, +]  ->   *   = [3, *]
    [+]     ->   *   = [*]
    [3]     ->   *   = [3, *]
     */
    private operator fun plus(operator: Operator): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> {
                Expression(values.dropLast(1) + operator)
            }
            is Int -> Expression(values + operator)
            null -> Expression()

            else -> throw IllegalArgumentException("연산자를 추가하는 과정에서 Error 발생")
        }
    }

    fun value(): String {
        return values.joinToString(" ") {
            if (it is Operator) it.operator else it.toString()
        }
    }
}

