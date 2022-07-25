package edu.nextstep.camp.calculator.domain

data class ExpressionRecord(
    val expression: Expression,
    val result: Int
) {
    override fun toString(): String {
        return "$expression\n= $result\n"
    }
}