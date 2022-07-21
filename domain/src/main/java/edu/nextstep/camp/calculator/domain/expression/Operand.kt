package edu.nextstep.camp.calculator.domain.expression

data class Operand(
    val number: Double
) : Expression {
    operator fun plus(another: Operand): Operand = Operand(number + another.number)
    operator fun minus(another: Operand): Operand = Operand(number - another.number)
    operator fun div(another: Operand): Operand = Operand(number / another.number)
    operator fun times(another: Operand): Operand = Operand(number * another.number)

    operator fun plus(operator: Operator): Operating = Operating(this, operator)

    override fun toString(): String {
        return number.let {
            when (it - it.toInt()) {
                0.0 -> "${it.toInt()}"
                else -> "$it"
            }
        }
    }
}

fun Int.operand() = Operand(this.toDouble())