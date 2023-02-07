package com.example.domain

data class Expression(
    private val values: List<Any> = emptyList()
) {

    /**
     값이 아무 것도 없을 경우, 값이 있을 경우
     */
    fun append(input: Any): Expression {
        if (values.isNullOrEmpty()) return Expression(listOf(input))

        return when (input) {
            is Operator -> plus(input)
            is Int -> plus(input)
            else -> throw IllegalArgumentException("error")
        }
    }

    /**
     들어올 케이스
     [3, +]   ->   1   = [3, +, 1]
     [3]      ->   1   = [31]
     [+]      ->   1   = [1]
     */
    private operator fun plus(operand: Int): Expression {
        return when (val last = values.last()) {
            is Operator -> {
                if (values.size == 1) return Expression(listOf(operand))
                Expression(values + operand)
            }
            is Int -> {
                val operand = "${values.last()}$last".toInt()
                Expression(values.dropLast(1) + operand)
            }
            else -> throw IllegalArgumentException()
        }
    }

    /**
     들어올 케이스
     [3, +]  ->   *   = [3, *]
     [+]     ->   *   = [*]
     [3]     ->   *   = [3, *]
     */
    private operator fun plus(operator: Operator): Expression {
        return when (val last = values.last()) {
            is Operator -> Expression(values.dropLast(1) + operator)
            is Int -> Expression(values + operator)
            else -> throw IllegalArgumentException()
        }
    }

    fun value(): List<Any> {
        return values
    }
}

