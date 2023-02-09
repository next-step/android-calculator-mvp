package camp.nextstep.edu.calculator

import java.util.function.IntBinaryOperator

enum class Operator(val value: String) : IntBinaryOperator {
    PLUS("+") {
        override fun applyAsInt(p0: Int, p1: Int): Int = p0 + p1
    },
    SUB("-") {
        override fun applyAsInt(p0: Int, p1: Int): Int = p0 - p1
    },
    MUL("*") {
        override fun applyAsInt(p0: Int, p1: Int): Int = p0 * p1
    },
    DIV("/") {
        override fun applyAsInt(p0: Int, p1: Int): Int = p0 / p1
    };

    companion object {

        fun toOperator(value: String): Operator? {
            return if (value.toIntOrNull() != null) null
            else Operator.values().find { it.value == value }
                ?: throw IllegalArgumentException("연산자가 아닙니다")

        }
    }
}