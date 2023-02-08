package com.example.domain

data class Expression(
    private val values: List<Any> = emptyList()
) {
    /**
    들어올 케이스
    [3, +]   ->   1   = [3, +, 1]
    [3]      ->   1   = [31]
    [+]      ->   1   = [1]
     */
    operator fun plus(operand: Int): Expression {
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
    operator fun plus(operator: Operator): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> {
                Expression(values.dropLast(1) + operator)
            }
            is Int -> Expression(values + operator)
            null -> Expression()

            else -> throw IllegalArgumentException("연산자를 추가하는 과정에서 Error 발생")
        }
    }

    fun dropLastInExpression(): Expression {
        if (values.isNullOrEmpty()) return Expression()

        return Expression(values.dropLast(1))
    }

    fun value(): String {
        return values.joinToString(" ") {
            if (it is Operator) it.operator else it.toString()
        }
    }
}

