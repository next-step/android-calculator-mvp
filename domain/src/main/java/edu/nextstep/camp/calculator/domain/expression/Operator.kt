package edu.nextstep.camp.calculator.domain.expression

enum class Operator(val operatorChar: Char) : Expression {
    Plus('+'),
    Minus('-'),
    Div('/'),
    Mult('*');

    operator fun invoke(left: Operand, right: Operand): Operand = when (this) {
        Plus -> left + right
        Minus -> left - right
        Div -> left / right
        Mult -> left * right
    }

    override fun toString(): String {
        return "$operatorChar"
    }

    companion object {
        fun find(char: Char): Operator = values().find { it.operatorChar == char } ?: throw IllegalArgumentException("잘못된 연산자가 포함되었습니다.")
        fun getChars() = values().map { it.operatorChar }.toCharArray()
    }
}