package com.example.domain

import com.example.domain.OperatorFinder.Companion.findOperator

object Calculator {

    fun calculate(input: String): Operand {
        require(input.isNotBlank()) { "빈 공백은 입력할 수 없습니다." }
        val inputOperands = splitList(input).filterIndexed { index, _ ->
            index % 2 == 0
        }

        val inputOperators = splitList(input).filterIndexed { index, _ ->
            index % 2 == 1
        }
        val operands = toOperandsList(inputOperands)
        val operators = toOperatorList(inputOperators)

        return operands.reduceIndexed { index, first, second ->
            operators[index - 1].calculate(first, second)
        }
    }

    private fun splitList(input: String): List<String> = input.split(" ")

    private fun toOperandsList(inputOperands: List<String>): List<Operand> = inputOperands.map {
        Operand(it.toBigDecimal())
    }

    private fun toOperatorList(inputOperators: List<String>): List<Operator> = inputOperators.map {
        findOperator(it)
    }
}
