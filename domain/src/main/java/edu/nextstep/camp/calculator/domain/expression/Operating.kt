package edu.nextstep.camp.calculator.domain.expression

data class Operating(
    private val left: Operand,
    private val operator: Operator
) : Expression {
    operator fun plus(right: Operand) = operator(left, right)
    override fun toString(): String {
        return "${this.left} ${this.operator}"
    }
}