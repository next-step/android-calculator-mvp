package edu.nextstep.camp.calculator.domain.expression

import kotlin.math.max

sealed interface Expression {

    companion object {
        val OPERATOR_SPLIT_REGEX = "[0-9\\.]".toRegex()

        fun merge(
            numbers: List<Expression>,
            operators: List<Expression>,
        ): List<Expression> {
            val expressions = arrayListOf<Expression>()
            (0..max(numbers.size, operators.size)).forEachIndexed { index, _ ->
                numbers.getOrNull(index)?.run(expressions::add)
                operators.getOrNull(index)?.run(expressions::add)
            }
            return expressions
        }

        @Throws(IllegalArgumentException::class)
        fun getOperators(text: String): List<Operator> {
            return text
                .split(OPERATOR_SPLIT_REGEX)
                .filter { it.isNotBlank() }
                .map { it.trim().also { trimmed -> if (trimmed.length > 1) throw IllegalArgumentException("잘못된 연산자가 포함되었습니다.") }.last() }
                .map { Operator.find(it) }
        }

        @Throws(IllegalArgumentException::class)
        fun getNumbers(text: String): List<Operand> {
            return text
                .replace(" ", "")
                .split(*Operator.getChars())
                .filter { it.isNotBlank() }
                .map { it.trim().toDoubleOrNull() ?: throw IllegalArgumentException("잘못된 연산자가 포함되었습니다.") }
                .map { Operand((it)) }
        }
    }
}