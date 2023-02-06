package com.example.domain

enum class Operator(
    private val operator: String,
    val evaluator: (Int, Int) -> Int
) {
    PLUS("+", {x,y -> x+y}),
    MINUS("-", {x,y -> x-y}),
    MULTI("*", {x,y -> x*y}),
    DIVIDE("/", {x,y -> x/y})
    ;

    companion object {
        fun of(operator: String): Operator {
            val isValidOperator = values().find { it.operator == operator }
            requireNotNull(isValidOperator) { "올바른 연산 기호가 아닙니다." }
            return isValidOperator
        }
    }
}