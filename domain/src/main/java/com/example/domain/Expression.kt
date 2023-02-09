package com.example.domain

data class Expression(
    private val values: List<Any> = emptyList()
) {

    operator fun plus(operand: Int): Expression {
        return when (val last = values.lastOrNull()) {
            is Operator -> {
                Expression(values + operand)
            }
            is Int -> {
                val nOperand = "$last$operand".toInt()
                Expression(values.dropLast(1) + nOperand)
            }
            null -> Expression(listOf(operand))

            else -> throw IllegalArgumentException("피연산자를 추가하는 과정에서 Error 발생")
        }
    }

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

    fun removeLastValue(): Expression {
        if (values.isNullOrEmpty()) return Expression()

        val last = values.last()
        if ((last is Int) && (last.toInt() > 9)) {
            return separateNumbers(last)
        }

        return Expression(values.dropLast(1))
    }

    private fun separateNumbers(last: Int): Expression {
        val last = (last / 10)
        return Expression(values.dropLast(1) + last)
    }

    fun value(): String {
        return values.joinToString(" ") {
            if (it is Operator) it.operator else it.toString()
        }
    }
}

