package com.example.domain

enum class Operator(
    val operator: String,
    val calculate: (Operand, Operand) -> Operand
) {
    PLUS("+", { first, second ->
        first.plus(second)
    }),

    MINUS("-", { first, second ->
        first.minus(second)
    }),

    TIMES("*", { first, second ->
        first.times(second)
    }),

    DIV("/", { first, second ->
        runCatching {
            first.div(second)
        }.getOrDefault(Operand(0))
    });
}

class OperatorFinder {
    companion object {
        fun findOperator(input: String): Operator = Operator.values().find { operator: Operator ->
            operator.operator == input
        } ?: throw IllegalArgumentException("잘못된 연산자 입니다.")
    }
}
