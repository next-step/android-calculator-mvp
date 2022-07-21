package edu.nextstep.camp.calculator.domain.expression

object EMPTY: Expression {
    override fun toString(): String = ""

    operator fun plus(value: Operand): Operand {
        return value
    }

    operator fun plus(value: Int): Operand {
        return Operand(value.toDouble())
    }

    operator fun plus(value: Operator) = EMPTY

}